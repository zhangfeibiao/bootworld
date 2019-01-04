package com.zfb.bootworld.service.permission;

import java.util.Set;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/4 17:29
 */
public interface PermissionService {

    /**
     * 通过账户ID查找所拥有资源权限
     *
     * @param userId
     * @return
     */
    Set<String> listPermissionByUserId(Integer userId);
}
