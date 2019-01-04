package com.zfb.bootworld.system.security;

import com.zfb.bootworld.entity.SysUser;
import com.zfb.bootworld.service.permission.PermissionService;
import com.zfb.bootworld.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


    /**
     * 通过登录账号加载业务用户信息,参数为用户账号
     *
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        // 通过业务账号获取业务系统的用户信息
        SysUser userLogin = userService.getUserLoginByName(userName);

        if (userLogin == null) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        if (0 != userLogin.getDisabled()) {
            throw new DisabledException("用户被禁用");
        }

        Set<String> perms = permissionService.listPermissionByUserId(userLogin.getId());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (!CollectionUtils.isEmpty(perms)) {
            perms.stream().filter(n -> n != null).forEach(perm -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(perm);
                grantedAuthorities.add(grantedAuthority);

            });

        }

        //将业务系统的用户信息以及权限信息封装到security的用户实体中
        return new User(userLogin.getUsername(), userLogin.getPassword(), grantedAuthorities);
    }
}