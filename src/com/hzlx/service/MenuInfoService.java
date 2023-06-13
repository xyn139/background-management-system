package com.hzlx.service;

import com.hzlx.entity.vo.MenuInfoVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MenuInfoService {
    /**
     * 根据用户ID 获取用户角色对应的菜单树
     * @param request 用于获取请求中的参数
     * @return
     */
    String showMenu(HttpServletRequest request);

    /**
     * 查询全部菜单
     * @param request
     */
    void getMenuList(HttpServletRequest request);

    String getMenu(HttpServletRequest request);

    String editMenu(HttpServletRequest request);

    String deleteMenu(HttpServletRequest request);

    /**
     * 新增菜单
     * @param request
     * @return
     */
    String addMenu(HttpServletRequest request);
}
