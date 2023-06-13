package com.hzlx.service;

import javax.servlet.http.HttpServletRequest;

public interface BusinessServlet {

    /**
     * 商家登录方法
     *
     * @param request http请求 ，用于获取商家提交的数据
     * @return json字符串
     */
    String businessLogin(HttpServletRequest request);

    /**
     * 根据roleName查询所有商家 支持模糊查询
     * @param request
     */
    void getBusinessInfoAllName(HttpServletRequest request);
    /**
     * 根据商家Id查询商家对象
     * @param request
     * @return
     */
    String getBusinessInfoId(HttpServletRequest request);

    /**
     * 新增商家 和修改商家 根据id来判断 如果id能取到值则为修改，反之新增
     * @param request
     * @return
     */
    String editBusiness(HttpServletRequest request);
    /**
     * 根据商家ID删除商家
     * @param request
     * @return
     */
    String updateBusinessStatus(HttpServletRequest request);
    /**
     * 批量启用
     * @param request
     * @return
     */
    String enableBusiness(HttpServletRequest request);
}
