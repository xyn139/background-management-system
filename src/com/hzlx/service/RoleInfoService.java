package com.hzlx.service;

import javax.management.relation.RoleInfo;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleInfoService {
    /**
     * 根据roleName查询所有角色 支持模糊查询
     * @param request
     */
    void getRoleInfoAllByName(HttpServletRequest request);

    /**
     * 根据角色ID查询角色对象
     * @param request
     * @return
     */
    String getRoleInfoAllById(HttpServletRequest request);

    /**
     * 新增角色 和修改角色 根据ID来判断 如果ID能取到值则角色能修改，反之新增
     * @param request
     * @return
     */
    String editRole(HttpServletRequest request);

    /**
     * 根据角色ID删除角色
     * @param request
     * @return
     */
    String updateRoleStatus(HttpServletRequest request);

    /**
     * 批量启用
     * @param request
     * @return
     */
    String enableRoles(HttpServletRequest request);
}
