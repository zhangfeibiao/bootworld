package com.zfb.bootworld.service.permission.impl;

import com.zfb.bootworld.service.permission.PermissionService;
import com.zfb.bootworld.service.permission.mapper.PermissionServiceMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/4 17:29
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionServiceMapper permissionServiceMapper;


    @Override
    public Set<String> listPermissionByUserId(Integer userId) {

        List<String> perms = permissionServiceMapper.listPermissionByUserId(userId);

        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
