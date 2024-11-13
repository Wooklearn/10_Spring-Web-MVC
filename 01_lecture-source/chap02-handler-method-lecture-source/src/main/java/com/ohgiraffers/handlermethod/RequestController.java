package com.ohgiraffers.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@RequestMapping("/request/*")
public class RequestController {

    /* title. 요청 시 값을 전달 받는 방법 */

    /* comment.
     *   view 페이지를 보여주는 방식
     *   1. String 타입 반환값에 view 파일 이름 작성
     *   2. 메소드의 반환 타입을 void
     *   - 반환 타입을 void 로 하게 되면 요청 url 이
     *   - view 의 이름이 된다.
     *  */

    @GetMapping("regist")
    public void regist() {
    }

    /* comment.
     *  1. WebRequest 객체로 요청 파라미터 전달 받기
     *  매개변수 선언부에 WebRequest 객체를 선언하면
     *  해당하는 핸들러 메소드가 호출 시 인자로 값을
     *  전달해준다.
     *  (서블릿 배울 때 doPost 메소드 내부에 HttpServletRequest)
     *  스프링 프레임워크는 내부적으로 Servlet 을 사용하고 있기
     *  때문에 HttpServletRequest 도 사용이 가능하다.
     *  다만 사용을 하지 않는 이유는 WebRequest 객체는
     *  Servlet 에 종속적이지 않으나 기능은 모두 포함하고 있기
     *  때문에 추후에 Servlet 기능이 사라졌을 시 적은 수정 범위로
     *  교체할 수 있다는 장점이 있다.
     *  WebRequest 객체는 Spring 측에서 제공하기 때문에
     *  Spring 친화적으로 많이 사용하는 편이다.
     *  */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {
        String menuName = request.getParameter("name");
        int menuPrice = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = menuName + "을(를) 신규 메뉴 목록" + categoryCode + "번 카테고리에 "
                + menuPrice + " 원 으로 등록했습니다.";

        model.addAttribute("message", message);

        return "request/printResult";
    }

    @GetMapping("modify")
    public void modify() {}

    /* comment.
    *   화면에서 요청하는 값을 담아주는 어노테이션이다.
    *   담을 매개변수 앞에 작성을 하게 되며
    *   form 의 name 속성과 일치시켜주어야 한다.
    *   만약에 당신이 짱구여서 말을 듣기 싫으면
    *   @RequestParam("폼name속성") String 사용하고 싶은 변수명
    *   name 속성이 일치하지 않을 때 400-bad request 에러가 발생한다.
    *   이는 required 속성의 기본값이 true 이기 때문이다.
    *   이 때 required 속성의 값을 false 로 바꿔주게 된다면
    *   해당하는 name 속성이 일치하지 않더라도 error 를 발생시키지 않고
    *   null 로 처리를 하게 된다.
    *  */
    @PostMapping("modify")
    public String modifyMenu(Model model,
                             @RequestParam(required = false) String name,
                             @RequestParam(defaultValue = "0") int price) {
        String message = name + "의 가격을 " + price + "로 수정!!";

        model.addAttribute("message", message);

        return "request/printResult";
    }

    /* comment.
     *   요청 파라미터가 여러 개인 경우 각각 담는 것이 아닌
     *   Map 을 사용해서 한번에 담을 수 있다.
     *   맵의 키는 form 태그의 name 속성 값이 된다. */
    @PostMapping("modifyAll")
    public String modifyAll(Model model, @RequestParam Map<String, String> parameters) {

        String menuName = parameters.get("modifyName2");
        int menuPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = menuName + "의 가격을 " + menuPrice + "로 수정!!";

        model.addAttribute("message", message);

        return "request/printResult";
    }

    @GetMapping("search")
    public void search() {}

    /* comment.
    *   지금은 요청하는 파라미터가 몇 개 안되어서
    *   @RequestParam 어노테이션을 사용해도 간단하게 작성할 수 있다.
    *   하지만, 받아 올 데이터가 많아진다면 관리할 변수나, 키값이
    *   많아질 수 밖에 없다.
    *   @ModelAttribute 객체를 생성하여 요청되는 값을 필드와
    *   form 태그의 name 속성과 비교하여 값을 넣어준다. */
    @PostMapping("search")
    public String searchMenu(@ModelAttribute MenuDTO menu) {

        System.out.println("menu = " + menu);

        return "request/searchResult";
    }


}
