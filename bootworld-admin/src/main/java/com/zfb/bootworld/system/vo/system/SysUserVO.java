package com.zfb.bootworld.system.vo.system;

import com.zfb.bootworld.entity.SysUser;
import lombok.Data;

/**
 * @author caizhixiang
 * @description: 系统用户信息返回实体
 * @Date 2018-08-30 12:12
 * @Version 1.0
 **/
@Data
public class SysUserVO extends SysUser {
    private String oldPwd;
}
