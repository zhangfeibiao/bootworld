package com.zfb.bootworld.service.user;

import com.zfb.bootworld.entity.SysUser;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/4 17:17
 */
public interface UserService {

    /**
     * 根据用户名查询账号信息
     *
     * @param userName
     * @return
     */
    SysUser getUserLoginByName(String userName);
}
