package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalTestController {

    @GetMapping("global-nullpointer")
    public String globalNull() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";
    }

    @GetMapping("global-userexception")
    public String globalUserException() throws MemberNotFoundException {
        boolean check = true;
        if(check) {
            throw new MemberNotFoundException("찾는 회원이 없어...");
        }
        return "/";
    }

    @GetMapping("global-default")
    public String manyException() {
        double[] array = new double[0];
        // 배열의 크기를 0으로 만들어두고
        // 1번째 데이터를 출력하려고 할 때
        System.out.println(array[0]);

        return "/";
    }

}
