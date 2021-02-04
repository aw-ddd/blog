package com.dwj.config;

import com.dwj.mapper.RequestPathMapper;
import com.dwj.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

//安全数据源
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private RequestPathMapper requestPathMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //查询具体某个接口的权限
        List<Permission> sysPermissionList = requestPathMapper.findPermissionByUrl(requestUrl);
        if (sysPermissionList == null || sysPermissionList.size() <= 0){
            //表明请求的路径没有配置任何的访问权限限制，该接口可以任意访问
            String[] attributes = new String[1];
            attributes[0] = "login_user";
            return  SecurityConfig.createList(attributes);//对无任何权限的接口添加默认权限login_user(所以注册用户至少需要一个login_user权限，不然无法访问内部资源)
//            return null;
        }
        String[] attributes = new String[sysPermissionList.size()];
        for (int i = 0; i < sysPermissionList.size(); i++) {
            attributes[i] = sysPermissionList.get(i).getPermissionCode();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
