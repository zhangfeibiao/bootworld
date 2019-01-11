package com.zfb.bootworld.system.vo.system;


import lombok.Data;

import java.util.List;

@Data
public class SysRoleVO {
    
    /**  */
    private Long id;

    /** 角色 */
    private String name;

    /**  */
    private Integer disabled;

    /** 描述 */
    private String roleCode;
    
	private Long userId;
	
	private Boolean isChecked = false;
	
	private List<Long> permissionIds;
	
 

}
