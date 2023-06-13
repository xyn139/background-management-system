package com.hzlx.dao;

import com.hzlx.entity.BusinessInfo;

import java.util.List;

public interface BusinessInfoDao {
    /**
     * 根据用户名和密码 查询用户信息
     * @param username  用户名
     * @param password  密码
     * @return  用户对象
     */
    BusinessInfo getBusinessInfoByUsernameAndPassword(String username, String password) ;

    /**
     * 根据 角色名字 模糊查询角色列表
     * @param name 角色名字
     * @return 角色集合
     */
    List<BusinessInfo> getBusinessInfoAllByName(String name);

    /**
     * 根据角色Id查询角色对象
     * @param id 角色ID
     * @return
     */

    BusinessInfo getBusinessInfoById(Integer id);

    int updateBusinessById(BusinessInfo businessInfo);

    int updateBusinessStatus(Integer id);

    int batchUpdateBusinessStatus(String[] ids);
    int addBusinessInfo(BusinessInfo businessInfo);

    List<BusinessInfo> getBusinessAll();
}
