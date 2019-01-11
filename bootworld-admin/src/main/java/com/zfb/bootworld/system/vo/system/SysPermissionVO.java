package com.zfb.bootworld.system.vo.system;

import lombok.Data;

import java.util.Date;

@Data
public class SysPermissionVO  {
	private static final long serialVersionUID = 1L;
	
    /**  */
    private Long id;

    /** 父菜单ID，一级菜单为0 */
    private Long parentId;

    /** 菜单名称 */
    private String name;

    /** 菜单URL */
    private String url;

    /** 授权(多个用逗号分隔，如：user:list,user:create) */
    private String perms;

    /** 类型   0：目录   1：菜单   2：按钮 */
    private Integer type;

    /** 菜单图标 */
    private String icon;

    /** 排序 */
    private Integer orderNum;

    /**  */
    private String method;

    /** 权限类型 1：菜单 2 资源 */
    private Integer permissionType;

    /** 排序 */
    private String sort;

    /**  */
    private Date gmtCreate;

    /**  */
    private Date gmtModified;

    /** 菜单名称 */
    private String description;
	
	private Long roleId;
	private Boolean isChecked;
}
