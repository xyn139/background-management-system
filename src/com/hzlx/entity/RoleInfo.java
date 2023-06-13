package com.hzlx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleInfo {
    private Integer id;
    private String name;
    private Date registrationTime;
    private Integer status;
}
