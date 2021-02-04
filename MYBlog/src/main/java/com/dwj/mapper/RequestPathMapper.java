package com.dwj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwj.pojo.Permission;
import com.dwj.pojo.RequestPath;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestPathMapper extends BaseMapper<RequestPath> {
    //查询某个接口的权限
    public List<Permission> findPermissionByUrl(@Param("url") String url);
}
