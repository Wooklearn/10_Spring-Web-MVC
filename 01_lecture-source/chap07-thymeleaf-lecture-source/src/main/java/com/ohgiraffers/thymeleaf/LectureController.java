package com.ohgiraffers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lecture/*")
public class LectureController {

    @GetMapping("expression")
    public ModelAndView expression(ModelAndView mv) {

        mv.addObject("member", new MemberDTO("김용욱", 20, '남', "서울"));

        mv.addObject("hello", "hi~<h2>타임리프</h2>");

        // 보여줄 뷰에 대한 경로 지정
        mv.setViewName("lecture/expression");

        return mv;
    }

    @GetMapping("conditional")
    public ModelAndView conditional(ModelAndView mv) {

        mv.addObject("num", 1);
        mv.addObject("str", "바나나");

        mv.setViewName("lecture/conditional");

        return mv;
    }

}
