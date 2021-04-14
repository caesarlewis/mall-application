package com.jasonless.mall.service.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasonless.mall.api.permission.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author LiuShiZeng
 */
@Repository
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT * FROM role_permission")
    List<Map<Integer, Integer>> allRolePermissions();

}
