package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.dao.RoleInfoDao;
import com.hzlx.dao.impl.RoleInfoDaoImpl;
import com.hzlx.service.RoleInfoService;
import com.hzlx.utils.BaseResult;
import com.mysql.cj.util.StringUtils;

import javax.management.relation.RoleInfo;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RoleInfoServiceImpl implements RoleInfoService {
    private RoleInfoDao roleInfoDao = new RoleInfoDaoImpl();

    @Override
    public void getRoleInfoAllByName(HttpServletRequest request) {
        //获取前端传来的 roleName
        String keyword = request.getParameter(BgmsConfig.KEYWORD);
        request.setAttribute(BgmsConfig.KEYWORD, keyword);
        request.setAttribute(BgmsConfig.ROLE_LIST, roleInfoDao.getRoleInfoAllByName(keyword));

    }

    @Override
    public String getRoleInfoAllById(HttpServletRequest request) {
        //获取前端传过来的Id
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            return BaseResult.success(roleInfoDao.getRoleInfoById(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(30001, "参数异常");
        }
    }

    @Override
    public String editRole(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int rows = 0;
        if (StringUtils.isNullOrEmpty(name)) {
            return BaseResult.error(30001, "参数异常");
        }
        if (StringUtils.isNullOrEmpty(id)) {
            //如果ID为空，执行新增
            rows = roleInfoDao.addRoleInfo(name);
        } else {
            //ID不为空，执行修改
            try {
                int roleId = Integer.parseInt(id);
                rows = roleInfoDao.updateRoleById(name, id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return BaseResult.error(30001, "参数异常");
            }

        }
        if (rows > 0) {
            return BaseResult.success();
        }
        return BaseResult.error(30002, "报错数据异常");
    }

    @Override
    public String updateRoleStatus(HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            int rows=roleInfoDao.updateRoleStatus(id);
            if (rows>0){
                return BaseResult.success();
            }else {
                return BaseResult.error(30003,"删除失败");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(30001,"参数异常");
        }
    }

    @Override
    public String enableRoles(HttpServletRequest request) {
        String idsSer = request.getParameter("ids");
        String[] ids = idsSer.substring(0, idsSer.length() - 1).split(",");
        int rows= roleInfoDao.batchUpdateRoleStatus(ids);
        if (rows>0){
            return BaseResult.success();
        }else {
            return BaseResult.error(30003,"启用失败");
        }
    }
}
