package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

    /* comment.
    *   Interceptor 가 Bean 에 개입할 수 있다는 것을
    *   보여주는 예시 클래스
    *  */

    public void method() {
        System.out.println("Service 계층의 메소드 호출됨...");
    }

}
