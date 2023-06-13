package com.hzlx.controller;

import com.hzlx.annotation.Controller;
import com.hzlx.annotation.RequestMapping;
import com.hzlx.annotation.ResponseBody;
import com.hzlx.service.BusinessServlet;
import com.hzlx.service.impl.BusinessServletImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/business")
@Controller
public class BusinessController {
    private BusinessServlet businessServlet = new BusinessServletImpl();

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return businessServlet.businessLogin(request);
    }


    @RequestMapping("/businessList")
    public String businessList(HttpServletRequest request, HttpServletResponse response) {
        businessServlet.getBusinessInfoAllName(request);
        return "pages/business_list";
    }

    @RequestMapping("/getBusiness")
    @ResponseBody
    public String getUserById(HttpServletRequest request, HttpServletResponse response) {
        return businessServlet.getBusinessInfoId(request);
    }

    @RequestMapping("/editBusiness")
    @ResponseBody
    public String editUser(HttpServletRequest request,HttpServletResponse response){
        return businessServlet.editBusiness(request);
    }
    @RequestMapping("/updateBusinessStatus")
    @ResponseBody
    public String updateRoleStatus(HttpServletRequest request, HttpServletResponse response) {
        return businessServlet.updateBusinessStatus(request);
    }

    @RequestMapping("/enableBusiness")
    @ResponseBody
    public String enableRoles(HttpServletRequest request, HttpServletResponse response) {
        return businessServlet.enableBusiness(request);
    }

}
