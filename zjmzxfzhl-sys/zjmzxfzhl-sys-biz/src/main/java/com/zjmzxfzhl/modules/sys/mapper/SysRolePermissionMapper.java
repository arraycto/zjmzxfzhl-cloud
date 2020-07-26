package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作权限Mapper
 *
 * @author 庄金明
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    /**
     * 查询操作权限列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<SysRolePermission> list(IPage<SysRolePermission> page, @Param("entity") SysRolePermission entity);
}
