package com.dwj.config;

import com.dwj.interceptor.CustomizeAbstractSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;//其实注入的是自定义的userDetailsService

    @Autowired
    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;//处理匿名用户无访问权限（即未登录或者登陆状态过期失效的情况下）

    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;//登录成功逻辑处理器

    @Autowired
    private CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;//登录失败逻辑处理器

    @Autowired
    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;//登出成功逻辑处理器

    @Autowired
    private CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;//会话信息过期策略

    @Autowired
    CustomizeAccessDecisionManager customizeAccessDecisionManager;//访问决策管理器

    @Autowired
    CustomizeFilterInvocationSecurityMetadataSource customizeFilterInvocationSecurityMetadataSource;//实现权限拦截

    @Autowired
    private CustomizeAbstractSecurityInterceptor customizeAbstractSecurityInterceptor;//权限拦截器

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        //登入
        http.formLogin()
                .loginProcessingUrl("/user/login").permitAll()
                .successHandler(customizeAuthenticationSuccessHandler)//使用登录成功逻辑处理器
                .failureHandler(customizeAuthenticationFailureHandler)//使用登录失败逻辑处理器
                .and().authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customizeAccessDecisionManager);//决策管理器
                        o.setSecurityMetadataSource(customizeFilterInvocationSecurityMetadataSource);//安全元数据源
                        return o;
                    }
                })
                .anyRequest().authenticated()
                //异常处理（权限拒绝，登录失效等）
                .and().exceptionHandling().authenticationEntryPoint(customizeAuthenticationEntryPoint)
                //登出
                .and().logout().permitAll()
                .logoutUrl("/user/logout")//定义登出路径
                .logoutSuccessHandler(customizeLogoutSuccessHandler)//使用登出成功逻辑处理器
                .deleteCookies("JSESSIONID")//登出成功之后删除cookie
                //会话状态管理
                .and().sessionManagement()
                .maximumSessions(1)//同一个账号只能一个用户使用
                .expiredSessionStrategy(customizeSessionInformationExpiredStrategy);//会话信息过期策略（账号被挤下线）
        http.addFilterBefore(customizeAbstractSecurityInterceptor, FilterSecurityInterceptor.class);
    }
}
