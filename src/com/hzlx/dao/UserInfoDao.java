package com.hzlx.dao;

import com.hzlx.entity.UserInfo;

import java.util.List;

public interface UserInfoDao {
    /**
     * 根据用户名和密码 查询用户信息
     * @param username  用户名
     * @param password  密码
     * @return  用户对象
     */
    UserInfo getUserInfoByUsernameAndPassword(String username, String password);

    int addUserInfo(UserInfo userInfo);

    /**
     * 根据用户名模糊查询角色列表
     * @param name
     * @return 角色集合
     */
    List<UserInfo> getUserInfoAllByName(String name);

    List<UserInfo> getUserInfoAll();

    /**
     * 根据用户ID查询角色对象
     * @param id 角色ID
     * @return
     */
    UserInfo getUserInfoById(Integer id);

    int updateUserById(UserInfo userInfo);

    int updateUserStatus(Integer id);

    int batchUpdateUserStatus(String[] ids);
}
