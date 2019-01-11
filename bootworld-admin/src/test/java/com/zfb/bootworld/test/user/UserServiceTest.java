package com.zfb.bootworld.test.user;

import com.zfb.bootworld.entity.SysUser;
import com.zfb.bootworld.service.user.UserService;
import com.zfb.bootworld.system.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhangfeibiao
 * @CreateDate: 2019/1/10 18:01
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserLoginByNameTest() {

        SysUser param = new SysUser();
        param.setUsername("test");
        SysUser user = userService.getUserLoginByName("test");
        System.out.println(user);

        throw new BusinessException("自定义异常");

    }
}
