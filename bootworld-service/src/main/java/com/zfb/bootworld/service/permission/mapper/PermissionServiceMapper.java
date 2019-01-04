package com.zfb.bootworld.service.permission.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/4 17:37
 */
@Mapper
public interface PermissionServiceMapper {

    /**
     * 根据账户ID查找所拥有资源
     *
     * @param userId
     * @return
     */
    List<String> listPermissionByUserId(Integer userId);
}
