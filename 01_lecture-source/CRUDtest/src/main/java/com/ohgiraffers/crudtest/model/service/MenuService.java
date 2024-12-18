package com.ohgiraffers.crudtest.model.service;

import com.ohgiraffers.crudtest.menu.controller.MenuDTO;
import com.ohgiraffers.crudtest.model.dao.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final Mapper mapper;

    public MenuService(Mapper mapper) {
        this.mapper = mapper;
    }

    public List<MenuDTO> menuList() {
        return mapper.menuList();
    }

    public MenuDTO getselectMenu(int code) {

        return mapper.selectMenu(code);
    }
}
