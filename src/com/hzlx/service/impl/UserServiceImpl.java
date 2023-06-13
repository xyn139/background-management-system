package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.dao.UserInfoDao;
import com.hzlx.dao.impl.UserInfoDaoImpl;
import com.hzlx.entity.UserInfo;
import com.hzlx.service.UserService;
import com.hzlx.utils.BaseResult;
import com.hzlx.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

//引用dao层，用户查询数据库
public class UserServiceImpl implements UserService {
   private UserInfoDao userInfoDao = new UserInfoDaoImpl();
   private   UserInfo userInfo = new UserInfo();

    @Override
    public String login(HttpServletRequest request) {

        //从请求之中获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //给密码加密
        String encryptPwd = MD5Utils.encryptMD5(password, username);

        //拿加密后的密码和用户名去数据库里查询用户信息
        UserInfo userInfo = userInfoDao.getUserInfoByUsernameAndPassword(username, encryptPwd);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return BaseResult.error(10001, "用户名或密码不能为空");
        }

        //如果查询到的 userInfo为空，则说明用户不存在，判定为账号或密码错误
        if (userInfo == null) {
            return BaseResult.error(10002, "账号或密码错误");
        }
        //用户登陆成功之后，把信息存放到 session 作用域中 用于后续使用
        request.getSession().setAttribute(BgmsConfig.SESSION_USER_KEY, userInfo);
        return BaseResult.success();
    }

    @Override
    public void getUserInfoAllName(HttpServletRequest request) {
        List<UserInfo> userInfoAll = userInfoDao.getUserInfoAll();
        request.getSession().setAttribute(BgmsConfig.USER_LIST, userInfoAll);
        //获取前端传过来的，username
        String keyword = request.getParameter(BgmsConfig.KEYWORD);
        request.setAttribute(BgmsConfig.KEYWORD, keyword);
        request.setAttribute(BgmsConfig.USER_LIST, userInfoDao.getUserInfoAllByName(keyword));


    }

    @Override
    public String getUserInfoId(HttpServletRequest request) {
        //获取前端传过来的 ID
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            return BaseResult.success(userInfoDao.getUserInfoById(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(40001, "参数异常");
        }
    }

    @Override
    public String editUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("username");
        int rows = 0;
        if (name == null) {
            return BaseResult.error(40001, "参数异常");
        }
        if (id == null || id == "") {
            //TODO 给UserInfo传值 前端的值set进去 传入dao中
            userInfo.setUserName(name);
            userInfo.setNickName(request.getParameter("nickName"));
            userInfo.setTel(request.getParameter("tel"));
            userInfo.setAddress(request.getParameter("address"));
            //如果id为空执行新增
            rows = userInfoDao.addUserInfo(userInfo);
        } else {
            //id不为空执行修改
            try {
                int userId = Integer.parseInt(id);
                userInfo.setId(userId);
                userInfo.setUserName(name);
                userInfo.setNickName(request.getParameter("nickName"));
                userInfo.setTel(request.getParameter("tel"));
                userInfo.setAddress(request.getParameter("address"));
                rows = userInfoDao.updateUserById(userInfo);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return BaseResult.error(40001, "参数异常");
            }
        }
        if (rows > 0) {
            return BaseResult.success();
        }
        return BaseResult.error(40002, "报错数据异常");
    }

    @Override
    public String updateUserStatus(HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            int rows = userInfoDao.updateUserStatus(id);
            if (rows > 0) {
                return BaseResult.success();
            } else {
                return BaseResult.error(40003, "删除失败");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(40001, "参数异常");
        }
    }

    @Override
    public String enableUser(HttpServletRequest request) {
        String idsSer = request.getParameter("ids");
        String[] ids = idsSer.substring(0, idsSer.length() - 1).split(",");
        int rows = userInfoDao.batchUpdateUserStatus(ids);
        if (rows > 0) {
            return BaseResult.success();
        } else {
            return BaseResult.error(40003, "启用失败");
        }
    }
}