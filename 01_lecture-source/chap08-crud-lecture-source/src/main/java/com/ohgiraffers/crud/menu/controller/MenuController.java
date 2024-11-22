package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu/*")
public class MenuController {

    /* comment.
     *   Logging
     *   어플리케이션이 실행 중 발생하는 이벤트(정보, 경고, 오류) 등을
     *   기록하는 과정.
     *   이는 사용자 화면을 위해 만드는 기능이 아닌, 개발자가 어플리케이션의
     *   상태를 추적하고, 모니터링 하는 데 사용할 수 있다. */

    private static final Logger logger = LogManager.getLogger(MenuController.class);

    private final MenuService menuService;
    /* bean 으로 등록한 메세지 소스 사용 */
    private final MessageSource messageSource;

    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    @GetMapping("list")
    public String findMenuList(Model model) {

        // 전체 메뉴 조회는 MenuDTO 타입이 여러 개 이기 때문에
        // List
        List<MenuDTO> menuList = menuService.findAllMenus();
        // DB 조회 값이 잘 들어있는 지 확인용
        for (MenuDTO menu : menuList) {
            System.out.println("menu = " + menu);
        }

        model.addAttribute("menuList", menuList);

        return "menu/list";
    }

    @GetMapping("regist")
    public void registPage() {
    }

    /* comment.
     *   @ResponseBody 어노테이션은
     *   기존의 Controller 의 역할은 view 를 마지막에
     *   리턴하는 것이 의무이다. 하지만, @ResponseBody
     *   는 view 를 리턴하는 의무가 아닌, 데이터를
     *   리턴할 수 있게 만든다.
     *   json -> 자바스크립트 객체 표기법을 의미한다.
     * */
    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

    @PostMapping("regist")
    public String registMenu(@ModelAttribute MenuDTO newMenu, RedirectAttributes rttr, Locale locale) {
        /* comment.
         *   @ModelAttribute : from 태그로 묶어서 넘어오는 값을 클래스 자료형에
         *   담기 위해 작성하는 어노테이션
         *   RedirectAttribute : 리다이랙트 시 저장할 값이 있으면 사용하는 객체
         * */

        /* comment.
         *   TRACE : 상세한 디버깅 정보(매우 세밀한 로그)
         *   DEBUG : 개발 중 디버깅용 정보
         *   INFO : 일반적인 실행 정보
         *   WARN : 잠재적인 문제 경고
         *   ERROR : 실행중 발생한 오류 */
        logger.info("Locale : {}", locale);
        logger.info("newMenu : {}", newMenu);

        menuService.registMenu(newMenu);
        rttr.addFlashAttribute("successMessage",
                messageSource.getMessage("regist", new Object[]{newMenu.getName(), newMenu.getPrice()}, locale));

        return "redirect:/menu/list";

    }

//    @GetMapping("categoryList")
//    public String categoryOneList(@ModelAttribute CategoryDTO param, Model model) {
////        @ModelAttribute CategoryDTO param (받아올 값),  Model model (되돌려 줄 값)
//
//        Integer code = param.getCode();
//        CategoryDTO category = new CategoryDTO();
//        List<CategoryDTO> categoryList = null;
//        if(code != null){
//            category.setCode(code);
//            categoryList = menuService.categorySearch(category);
//            model.addAttribute("categoryList", categoryList);
//        }
//
//        return "/menu/categoryList";
//    }

    @GetMapping("join/list")
    public String menuAndCategoryList(Model model) {

        List<MenuAndCategoryDTO> joinList = menuService.findAllMenuAndCategory();
        System.out.println("joinList = " + joinList);

        model.addAttribute("joinList", joinList);

        return "/menu/join";
    }

    /* comment.
    *   DELETE 구문 생성
    *   인덱스 페이지에서 delete 버튼 누르면
    *   메뉴코드를 입력할 수 있는 input 태그와
    *   전송버튼을 보여주는 view 페이지로 이동
    *   -
    *   이후 값 전달 받아 삭제하는 기능 생성
    *   전송 버튼 누르면 menu/list 페이지로 redirect 진행
    *   리다이랙트 시 사용자에게 alert 창으로
    *   몇 번 메뉴 삭제 완료되었습니다. 메세지 출력
    *  */
    @GetMapping("delete")
    public String menudelete() {
        return "menu/delete";
    }

    @PostMapping
    public String deleteList(@RequestParam int code) {

        menuService.deleteMenu(code);

        return "redirect:/menu/list";

    }

    @GetMapping("code")
    public String menuDetail(@RequestParam("code") String code, Model model){

        System.out.println("코드 확인용 : " + code);

        MenuDTO menu = menuService.menuDetail(code);

        System.out.println("menu = " + menu);

        model.addAttribute("menuDetail", menu);

        return "menu/menuDetail";
    }

    @GetMapping("update")
    public String updateMenu(@RequestParam("code") String code, Model model){

        MenuDTO menu = menuService.menuDetail(code);

        model.addAttribute("menu", menu);

        return "menu/update";
    }

//    @PostMapping("update")
//    public String updateMenuResult(@ModelAttribute MenuDTO menu, RedirectAttributes rttr, Locale locale){
//        System.out.println("메뉴 값 확인 : " + menu);
//
//        menuService.updateMenu(menu);
//
//        rttr.addFlashAttribute("successMessage",
//                messageSource.getMessage("update", new Object[]{menu.getName(), menu.getPrice()}, locale));
//
//        return "redirect:/menu/menuDetail?code=" + menu.getCode();
//    }

}
