package com.ohgiraffers.crud.menu.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuAndCategoryDTO {

    private int code;
    private String name;
    private int price;
    private String orderableStatus;
    private CategoryDTO categoryDTO;

}
