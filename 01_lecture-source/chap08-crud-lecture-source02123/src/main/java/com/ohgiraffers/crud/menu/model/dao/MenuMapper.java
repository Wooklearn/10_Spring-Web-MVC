package com.ohgiraffers.crud.menu.model.dao;


import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/* comment.
*   Mybatis 의 전용 어노테이션으로 Repository 의
*   더 구체적인 기능을 가진 어노테이션이다.
* */
@Mapper
public interface MenuMapper {

    List<MenuDTO> findMenu();

    List<MenuDTO> findAllMenus();

    List<CategoryDTO> findAllCategory();

    void registNewMenu(MenuDTO newMenu);

    List<CategoryDTO> categoryList(CategoryDTO category);

    List<MenuDTO> menuAll();

    void menuDelete(MenuDTO menuDTO);

    void menuUpdate(MenuDTO menu);

    MenuDTO menuDetail(String code);
}
