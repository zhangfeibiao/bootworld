package com.zfb.bootworld.service.user.mapper;

import com.zfb.bootworld.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/10 16:50
 */
@Mapper
public interface UserServiceMapper {

    SysUser getUserLoginByName(SysUser user);
}
