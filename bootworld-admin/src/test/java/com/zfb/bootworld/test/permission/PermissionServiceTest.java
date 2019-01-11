package com.zfb.bootworld.test.permission;

import com.zfb.bootworld.service.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/11 10:24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void PermissionTest() {

        Set<String> strings = permissionService.listPermissionByUserId(1);
        System.out.println(strings);
    }
}

