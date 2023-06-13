package com.hzlx.controller;

import com.hzlx.annotation.Controller;
import com.hzlx.annotation.RequestMapping;
import com.hzlx.annotation.RequestParameter;
import com.hzlx.annotation.ResponseBody;
import com.hzlx.entity.UserInfo;
import com.hzlx.service.UserService;
import com.hzlx.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/user")
public class UserInfoController {

    private UserService userService = new UserServiceImpl();

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return userService.login(request);
    }


    @RequestMapping("/userList")
    public String userList(HttpServletRequest request, HttpServletResponse response) {
        userService.getUserInfoAllName(request);
        return "pages/user_list";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public String getUserById(HttpServletRequest request, HttpServletResponse response) {
        return userService.getUserInfoId(request);
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public String editUser(HttpServletRequest request,HttpServletResponse response){
        return userService.editUser(request);
    }
    @RequestMapping("/updateUserStatus")
    @ResponseBody
    public String updateRoleStatus(HttpServletRequest request, HttpServletResponse response) {
        return userService.updateUserStatus(request);
    }

    @RequestMapping("/enableUser")
    @ResponseBody
    public String enableRoles(HttpServletRequest request, HttpServletResponse response) {
        return userService.enableUser(request);
    }

}
