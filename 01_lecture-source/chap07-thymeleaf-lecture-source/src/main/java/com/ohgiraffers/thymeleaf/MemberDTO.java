package com.ohgiraffers.thymeleaf;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private String name;
    private int age;
    private char gender;
    private String address;

}
