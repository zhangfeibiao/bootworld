package com.zfb.bootworld.system.config;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.zfb.bootworld.system.common.Response;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangfeibiao
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccess authenticationSuccess;

    @Autowired
    private AuthenticationFailure authenticationFailure;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SessionRegistry sessionRegistry;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方法;
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        web.ignoring().antMatchers("/static/**","/css/**","/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and().authorizeRequests()
            //antMatchers无需权限 即可访问，permitAll面向全部用户开放 除了antmatchers中的例外，其他任何请求都需要权限认证
                    .antMatchers("/login").permitAll()
                    .antMatchers("/loginPageAjax").permitAll()
                    .antMatchers("/logout").permitAll()
                    .antMatchers("/static/**").permitAll()
            .anyRequest().authenticated()
            //formlogin登录配置,and()是链接符，and之间的内容有相同的作用域
            .and().formLogin().loginPage("/login")
            .successHandler(new AuthenticationSuccessHandler() {
                //登陆成功处理
                @Override
                public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write(JSON.toJSONString(Response.success("登录成功")));
                    out.flush();
                    out.close();
                }
            })
            .failureHandler(new AuthenticationFailureHandler() {
                //登录失败的处理
                @Override
                public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();

                    String errorMsg = e.getMessage();
                    if ("Bad credentials".equals(errorMsg)) {
                        errorMsg = "密码错误";
                    }
                    out.write(JSON.toJSONString(Response.failure(errorMsg)));
                    out.flush();
                    out.close();
                }
            })
            // .defaultSuccessUrl("/index")
            .and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry)
            .and().and().logout().invalidateHttpSession(true)
            //设置登出后跳转的链接，一般设置登录页面，登录请求也是security内置的默认为logout,自定义登录链接用.logoutUrl(logoutUrl)
            .and().logout().logoutSuccessUrl("/login").permitAll()
            .clearAuthentication(true);

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
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository =
                new HttpSessionSecurityContextRepository();
        return httpSessionSecurityContextRepository;
    }

    @Bean
    public SessionRegistry getSessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }
}