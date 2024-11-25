package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

//@RestController
@Controller
@RequestMapping("/menu/*")
public class MenuController {

    private final MenuService menuService;
    /* bean 으로 등록한 메세지 소스 사용 */
    private final MessageSource messageSource;

    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    @GetMapping("test")
    public String findMenu(Model model) {

        List<MenuDTO> menuList = menuService.findMenu();
        model.addAttribute("menuList", menuList);

        return "menu/list";
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

        menuService.registMenu(newMenu);
        rttr.addFlashAttribute("successMessage",
                messageSource.getMessage("regist", new Object[]{newMenu.getName(), newMenu.getPrice()}, locale));

        return "redirect:/menu/list";

    }


    @GetMapping("categoryList")
    public String categoryOneList(@ModelAttribute CategoryDTO param, Model model) {
//        @ModelAttribute CategoryDTO param (받아올 값),  Model model (되돌려 줄 값)

        Integer code = param.getCode();
        CategoryDTO category = new CategoryDTO();
        List<CategoryDTO> categoryList = null;
        if (code != null) {
            category.setCode(code);
            categoryList = menuService.categorySearch(category);
            model.addAttribute("categoryList", categoryList);
        }

        return "/menu/categoryList";
    }

    @GetMapping("allList")
    public String allListSearch(Model model) {

        List<MenuDTO> menuList = menuService.allList();

        model.addAttribute("menuList", menuList);

        return "menu/menuList";

    }

    @GetMapping("delete")
    public String deleteMenu(@ModelAttribute MenuDTO menuDTO, RedirectAttributes rttr, Model model, Locale locale) {

        menuService.menuDelete(menuDTO);


        rttr.addFlashAttribute("successMessage",
                messageSource.getMessage("delete", new Object[]{}, locale));

        return "redirect:/menu/allList";
    }

    @GetMapping("update")
    public String updateMenu(@ModelAttribute MenuDTO menu, RedirectAttributes rttr, Locale locale) {

        menuService.updateMenu(menu);

        rttr.addFlashAttribute("successMessage",
                messageSource.getMessage("update", new Object[]{}, locale));

        return "redirect:/menu/menuDetail?code=";
    }


    @GetMapping("menuDetail")
    public String menuDetail(@RequestParam("code") String code, Model model) {

        MenuDTO menu = menuService.menuDetail(code);

        model.addAttribute("menuDetail", menu);

        return "/menu/menuDetail";

    }
}
