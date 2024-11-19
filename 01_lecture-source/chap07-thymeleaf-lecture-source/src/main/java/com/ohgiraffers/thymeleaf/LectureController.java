package com.ohgiraffers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberList.add(new MemberDTO("시진핑", 6, '남', "베이징 사천구"));
        memberList.add(new MemberDTO("티니핑", 8, '남', "서울시 광진구"));
        memberList.add(new MemberDTO("핑구", 4, '남', "서울시 핑구"));

        mv.addObject("memberList", memberList);

        mv.setViewName("lecture/conditional");

        return mv;
    }

    @GetMapping("etc")
    public ModelAndView etc(ModelAndView mv) {

        SearchCriteria criteria = new SearchCriteria(1, 10, 3);

        // key = value 형식으로 저장 가능하지만, key 를 작성하지 않을 시
        // 인스턴스의 타입 = 클래스 명이 곧 키값이 된다.
        mv.addObject(criteria);

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberList.add(new MemberDTO("시진핑", 6, '남', "베이징 사천구"));
        memberList.add(new MemberDTO("티니핑", 8, '남', "서울시 광진구"));
        memberList.add(new MemberDTO("핑구", 4, '남', "서울시 핑구"));

        mv.addObject("memberList", memberList);

        Map<String, MemberDTO> memberMap = new HashMap<>();
        memberMap.put("1", new MemberDTO("하츄핑", 4, '여', "서울시 노진구"));
        memberMap.put("2", new MemberDTO("시진핑", 6, '남', "베이징 사천구"));
        memberMap.put("3", new MemberDTO("티니핑", 8, '남', "서울시 광진구"));
        memberMap.put("4", new MemberDTO("핑구", 4, '남', "서울시 핑구"));

        mv.addObject("memberMap", memberMap);

        mv.setViewName("lecture/etc");

        return mv;
    }


}
