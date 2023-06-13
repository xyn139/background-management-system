package com.hzlx.dao.impl;

import com.hzlx.dao.BusinessInfoDao;
import com.hzlx.entity.BusinessInfo;
import com.hzlx.utils.BaseDao;
import com.mysql.cj.util.StringUtils;

import java.util.List;

public class BusinessDaoImpl extends BaseDao<BusinessInfo> implements BusinessInfoDao {

    @Override
    public BusinessInfo getBusinessInfoByUsernameAndPassword(String username, String password) {
        return selectOne("select * from business_info where user_name=? and `password`=?",BusinessInfo.class,username,password);
    }

    @Override
    public List<BusinessInfo> getBusinessInfoAllByName(String name) {
        String sql = "select * from business_info";
        if (StringUtils.isNullOrEmpty(name)) {
            sql += " and name like concat('%',?,'%')";
            return selectListForObject(sql, BusinessInfo.class, name);
        }
        return selectListForObject(sql, BusinessInfo.class);
    }

    @Override
    public BusinessInfo getBusinessInfoById(Integer id) {
        String sql = "select * from business_info where id=?";
        return selectOne(sql, BusinessInfo.class, id);
    }

    /**
     * 修改
     *
     * @param businessInfo
     * @return
     */
    @Override
    public int updateBusinessById(BusinessInfo businessInfo) {
        String sql = "update business_info set name=?,user_name=?,br_name=?,tel=?,address=? where id=?";
        return executeUpdate(sql,
                businessInfo.getName(),
                businessInfo.getUserName(),
                businessInfo.getBrName(),
                businessInfo.getTel(),
                businessInfo.getAddress(),
                businessInfo.getId()
        );
    }

    @Override
    public int updateBusinessStatus(Integer id) {
        String sql="update business_info set `status`=IF(`status`=1,0,1) where id=?";
        return executeUpdate(sql,id);
    }

    @Override
    public int batchUpdateBusinessStatus(String[] ids) {
       String sql="update business_info set `status`=1 where id in(";
        for (int i = 0; i <ids.length; i++) {
            if (i==ids.length-1){
                sql+=" ?";
            }else {
                sql+=" ?,";
            }
        }
        sql+=")";
        return executeUpdate(sql,ids);
    }

    @Override
    public int addBusinessInfo(BusinessInfo businessInfo) {
        String sql = "insert into business_info values(default,?,?,default,?,?,?,null,now(),default,default)";
        return executeUpdate(sql,
                businessInfo.getName(),
                businessInfo.getUserName(),
                businessInfo.getBrName(),
                businessInfo.getTel(),
                businessInfo.getAddress()
        );
    }

    @Override
    public List<BusinessInfo> getBusinessAll() {
        return selectListForObject("select * from business_info", BusinessInfo.class);
    }

}
