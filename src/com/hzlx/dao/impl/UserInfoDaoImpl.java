package com.hzlx.dao.impl;

import com.hzlx.dao.UserInfoDao;
import com.hzlx.entity.UserInfo;
import com.hzlx.utils.BaseDao;
import com.mysql.cj.util.StringUtils;

import java.util.List;

public class UserInfoDaoImpl extends BaseDao<UserInfo> implements UserInfoDao {
    @Override
    public UserInfo getUserInfoByUsernameAndPassword(String username, String password) {
        return selectOne("select * from user_info where user_name=? and `password`=?", UserInfo.class, username, password);
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        String sql = "insert into user_info values(null,?,null,?,?,?,now(),default)";
        return executeUpdate(sql,
                userInfo.getUserName(),
                userInfo.getNickName(),
                userInfo.getTel(),
                userInfo.getAddress()
        );
    }

    @Override
    public List<UserInfo> getUserInfoAllByName(String name) {
        String sql = "select * from user_info";
        if (!StringUtils.isNullOrEmpty(name)) {
            sql +=" where user_name like concat('%',?,'%')";
            return selectListForObject(sql, UserInfo.class, name);
        }
        return selectListForObject(sql, UserInfo.class);
    }

    @Override
    public List<UserInfo> getUserInfoAll() {
        return selectListForObject("select * from user_info", UserInfo.class);
    }

    @Override
    public UserInfo getUserInfoById(Integer id) {
        String sql = "select * from user_info where id=?";
        return selectOne(sql, UserInfo.class, id);
    }

    @Override
    public int updateUserById(UserInfo userInfo) {
        String sql = "update user_info set user_name=?,nick_name=?,tel=?,address=? where id=?";
        return executeUpdate(sql,
                userInfo.getUserName(),
                userInfo.getNickName(),
                userInfo.getTel(),
                userInfo.getAddress()
                ,userInfo.getId()
                );
    }

    @Override
    public int updateUserStatus(Integer id) {
        String sql = "update user_info set `status`=IF(`status`=1,0,1) where id=?";
        return executeUpdate(sql, id);
    }

    @Override
    public int batchUpdateUserStatus(String[] ids) {
        String sql = "update user_info set `status`=1 where id in(";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sql += " ?";
            } else {
                sql += " ?,";
            }
        }
        sql += ")";
        return executeUpdate(sql,ids);
    }

}
