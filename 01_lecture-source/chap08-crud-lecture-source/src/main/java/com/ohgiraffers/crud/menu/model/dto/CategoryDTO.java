package com.ohgiraffers.crud.menu.model.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Alias("category")
public class CategoryDTO {
    private int code;
    private String name;
    private Integer refCategoryCode;
}
