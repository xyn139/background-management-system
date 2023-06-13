package com.hzlx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuInfo {
    private Integer id;
    private String pageShows;
    private String icon;
    private String href;
    private Integer pId;
    private Integer status;
    private Date createTime;

}
