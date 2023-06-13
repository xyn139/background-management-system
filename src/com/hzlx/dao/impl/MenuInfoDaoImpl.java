package com.hzlx.dao.impl;

import com.hzlx.dao.MenuInfoDao;
import com.hzlx.entity.MenuInfo;

import com.hzlx.utils.BaseDao;
import com.mysql.cj.util.StringUtils;

import java.util.List;
import java.util.Map;

public class MenuInfoDaoImpl extends BaseDao<MenuInfo> implements MenuInfoDao {

    @Override
    public List<MenuInfo> getMenuInfoListByPid(Integer userId, Integer pId) {
        String sql1 = "select * from menus_info where `status`=1 and p_id=?";
        String sql = "select mi.* from menus_info mi\n" +
                "join roles_menu_info rmi on rmi.menu_id=mi.id\n" +
                "join role_user_info rui on rui.role_id = rmi.role_id\n" +
                "where rui.user_id=? and mi.status=1 and mi.p_id=?";
        return selectListForObject(sql, MenuInfo.class, userId, pId);
    }

    @Override
    public List<Map<String, Object>> getMenuAll(String keyword) {
        String sql = "SELECT mi.*,mi1.Page_shows AS pName\n" +
                "FROM menus_info mi\n" +
                "Left JOIN menus_info mi1 on mi1.id=mi.p_id\n" ;
        if (!StringUtils.isNullOrEmpty(keyword)) {
            sql += " where mi.Page_shows like concat('%',?,'%')";
            return selectListForMap(sql, keyword);
        }
        return selectListForMap(sql);
    }

    @Override
    public MenuInfo getMenuInfoById(Integer id) {
        String sql = "select * from menus_info where id=?";
        return selectOne(sql, MenuInfo.class, id);
    }


    @Override
    public int updateMenuInfoById(Integer id, String pageShows, String icon,String href,Integer pId) {
        String sql = "update menus_info set Page_shows=?,icon=?,href=?,pId=? where id=?";
        return executeUpdate(sql, pageShows, icon,href,pId, id);
    }

    @Override
    public int deleteMenuInfoById(Integer id) {
        String sql = "update menus_info set `status`=0 where id=?";
        return executeUpdate(sql, id);
    }



    @Override
    public List<MenuInfo> getMenuInfoByPid(Integer pId) {
        String sql="select * from menus_info where `status`=1 and p_id=?";
        return selectListForObject(sql,MenuInfo.class,pId);
    }

    @Override
    public int saveMenu(String pageShow, String icon, String href, Integer pId) {
        String sql="insert into menus_info values(null,?,?,?,?,default,now())";
        return executeUpdate(sql,pageShow,icon,href,pId);
    }
}
