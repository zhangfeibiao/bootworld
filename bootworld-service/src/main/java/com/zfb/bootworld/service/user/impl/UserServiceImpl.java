package com.zfb.bootworld.service.user.impl;

import com.zfb.bootworld.entity.SysUser;
import com.zfb.bootworld.mapper.SysUserMapper;
import com.zfb.bootworld.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/4 17:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserLoginByName(String userName) {
        SysUser param = new SysUser();
        param.setUsername(userName);

        SysUser sysUser = sysUserMapper.selectOne(param);

        return sysUser;
    }
}
