package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysRoleUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色和用户关系Mapper
 *
 * @author 庄金明
 */
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {
    /**
     * 查询角色和用户关系列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<SysRoleUser> list(IPage<SysRoleUser> page, @Param("entity") SysRoleUser entity);
}
