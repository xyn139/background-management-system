package com.hzlx.service;

import com.hzlx.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {


    /**
     * 用户登录方法
     *
     * @param request http请求 ，用于获取用户提交的数据
     * @return json字符串
     */
    String login(HttpServletRequest request);

    /**
     * 根据roleName查询所有用户 支持模糊查询
     * @param request
     */
    void getUserInfoAllName(HttpServletRequest request);
    /**
     * 根据用户Id查询用户对象
     * @param request
     * @return
     */
    String getUserInfoId(HttpServletRequest request);

    /**
     * 新增用户 和修改用户 根据id来判断 如果id能取到值则为修改，反之新增
     * @param request
     * @return
     */
    String editUser(HttpServletRequest request);
    /**
     * 根据角色ID删除用户
     * @param request
     * @return
     */
    String updateUserStatus(HttpServletRequest request);
    /**
     * 批量启用
     * @param request
     * @return
     */
    String enableUser(HttpServletRequest request);
}
