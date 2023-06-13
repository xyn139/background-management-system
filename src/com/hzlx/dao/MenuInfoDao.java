package com.hzlx.dao;

import com.hzlx.entity.MenuInfo;


import java.util.List;
import java.util.Map;

public interface MenuInfoDao {
    /**
     * 根据用户ID和Pid 查询菜单集合
     * @param userId
     * @param pId
     * @return
     */
    List<MenuInfo> getMenuInfoListByPid(Integer userId,Integer pId);

     List<Map<String ,Object>> getMenuAll(String keyword);

    MenuInfo getMenuInfoById(Integer id);

    int updateMenuInfoById(Integer id, String pageShows, String icon,String href,Integer pId);

    int deleteMenuInfoById(Integer id);

    List<MenuInfo> getMenuInfoByPid(Integer pId);

    int saveMenu(String pageShow,String icon,String href,Integer pId );
}
