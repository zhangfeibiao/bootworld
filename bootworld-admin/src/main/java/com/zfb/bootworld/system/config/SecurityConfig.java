package com.zfb.bootworld.system.config;

import com.zfb.bootworld.system.security.CustomUserDetailsService;
import com.zfb.bootworld.system.security.AuthenticationFailure;
import com.zfb.bootworld.system.security.AuthenticationSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * @author zhangfeibiao
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccess authenticationSuccess;

    @Autowired
    private AuthenticationFailure authenticationFailure;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方法;
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.authorizeRequests()
         //antMatchers无需权限 即可访问，permitAll面向全部用户开放
        .antMatchers("/login","/swagger-ui.html").permitAll()
        //除了antmatchers中的例外，其他任何请求都需要权限认证
        .anyRequest().authenticated()
        //formlogin登录配置,and()是链接符，and之间的内容有相同的作用域
        .and().formLogin().loginPage("/login")
        .successHandler(authenticationSuccess)//登陆成功处理
        // .failureHandler(authenticationFailure)//登录失败的处理
        //登录验证处理请求，请求逻辑是security内置的，此处只设置自己喜欢的请求就可以了，然后在表单中提交的请求要与此处设置的一致即可
        .loginProcessingUrl("/login")
       //设置security内置的请求表单元素的name名称，此处设置的要与登录表单的用户名密码的name一致 .usernameParameter("loginname").passwordParameter("password").permitAll()
        .and().headers().frameOptions().disable()
        //设置登出后跳转的链接，一般设置登录页面，登录请求也是security内置的默认为logout,自定义登录链接用.logoutUrl(logoutUrl)
        .and().logout().logoutSuccessUrl("/login").permitAll()
        .and().csrf().disable();// 关闭csrf防护
    }

     //设置用户信息获取方法，获取方法是自定义的实现security提供的接口即可
     //就是通过自定义接口实现获取业务用户的信息包括用户名，密码，权限，交给security处理
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }
    @Bean
    public SecurityContextRepository securityContextRepository() {
      //设置对spring security的UserDetails进行session保存,这个必须要有，不然不会保存至session对应的缓存redis中
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository = new HttpSessionSecurityContextRepository();
        return httpSessionSecurityContextRepository;
    }
}