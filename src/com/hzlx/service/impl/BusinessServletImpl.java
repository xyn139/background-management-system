package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.dao.BusinessInfoDao;
import com.hzlx.dao.impl.BusinessDaoImpl;
import com.hzlx.entity.BusinessInfo;
import com.hzlx.service.BusinessServlet;
import com.hzlx.utils.BaseResult;
import com.hzlx.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BusinessServletImpl implements BusinessServlet {

    private BusinessInfoDao businessInfoDao = new BusinessDaoImpl();
    private BusinessInfo businessInfo = new BusinessInfo();

    @Override
    public String businessLogin(HttpServletRequest request) {
        //从请求之中获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //给密码加密
        String encryptPwd = MD5Utils.encryptMD5(password, username);

        //那加密后的密码和用户名去数据库查询用户信息
        BusinessInfo businessInfo1 = businessInfoDao.getBusinessInfoByUsernameAndPassword(username, encryptPwd);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return BaseResult.error(10001, "用户或密码不能为空");
        }
        //用户登陆成功之后，把信息存放到 session 作用域中 用于后续使用
        request.getSession().setAttribute(BgmsConfig.SESSION_USER_KEY, businessInfo1);
        return BaseResult.success();
    }

    @Override
    public void getBusinessInfoAllName(HttpServletRequest request) {
        List<BusinessInfo> businessInfos = businessInfoDao.getBusinessAll();
        request.getSession().setAttribute(BgmsConfig.BUSINESS_LIST, businessInfos);
        //获取前端传过来的， username
        String keyword = request.getParameter(BgmsConfig.KEYWORD);
        request.setAttribute(BgmsConfig.KEYWORD, keyword);
        request.setAttribute(BgmsConfig.BUSINESS_LIST, businessInfoDao.getBusinessInfoAllByName(keyword));
    }

    @Override
    public String getBusinessInfoId(HttpServletRequest request) {
        //获取前端传过来的 ID
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            return BaseResult.success(businessInfoDao.getBusinessInfoById(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(50001, "参数异常");
        }
    }

    @Override
    public String editBusiness(HttpServletRequest request) {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String name=request.getParameter("name");
        int rows = 0;
        if (username == null) {
            return BaseResult.error(50001, "参数异常");
        }
        if (id == null || id == "") {
            //TODO 给business传值前端的值set进去 传入dao中
            businessInfo.setName(name);
            businessInfo.setUserName(username);
            businessInfo.setBrName(request.getParameter("brName"));
            businessInfo.setTel(request.getParameter("tel"));
            businessInfo.setAddress(request.getParameter("address"));
            //如果id为空执行新增
            rows = businessInfoDao.addBusinessInfo(businessInfo);
        } else {
            //id不为空执行修改
            try {
                int businessId = Integer.parseInt(id);
                businessInfo.setId(businessId);
                businessInfo.setName(name);
                businessInfo.setUserName(request.getParameter("username"));
                businessInfo.setBrName(request.getParameter("brName"));
                businessInfo.setTel(request.getParameter("tel"));
                businessInfo.setAddress(request.getParameter("address"));
                rows = businessInfoDao.updateBusinessById(businessInfo);

            } catch (NumberFormatException e) {
                e.printStackTrace();
                return BaseResult.error(50001, "参数异常");
            }
        }
        if (rows > 0) {
            return BaseResult.success();
        }
        return BaseResult.error(50002,"报错数据异常");
    }

    @Override
    public String updateBusinessStatus(HttpServletRequest request) {
        try {
            Integer id=Integer.parseInt(request.getParameter("id"));
            int rows=businessInfoDao.updateBusinessStatus(id);
            if (rows>0){
                return BaseResult.success();
            }else {
                return BaseResult.error(50003,"删除失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(50001,"参数异常");
        }
    }

    @Override
    public String enableBusiness(HttpServletRequest request) {
        String idSer=request.getParameter("ids");
        String[] ids = idSer.substring(0, idSer.length() - 1).split(",");
        int rows=businessInfoDao.batchUpdateBusinessStatus(ids);
        if (rows>0){
            return BaseResult.success();
        }else {
            return BaseResult.error(50003,"启用失败");
        }
    }
}
