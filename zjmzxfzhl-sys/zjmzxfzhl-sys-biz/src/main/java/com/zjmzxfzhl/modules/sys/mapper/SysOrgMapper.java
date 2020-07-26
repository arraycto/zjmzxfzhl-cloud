package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.common.core.aspect.annotation.DataPermission;
import com.zjmzxfzhl.modules.sys.entity.SysOrg;
import com.zjmzxfzhl.modules.sys.permission.provider.OrgDataPermissionProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构Mapper
 *
 * @author 庄金明
 */
public interface SysOrgMapper extends BaseMapper<SysOrg> {
    /**
     * 查询机构列表
     *
     * @param page
     * @param entity
     * @return
     */
    @DataPermission(providers = OrgDataPermissionProvider.class)
    List<SysOrg> list(IPage<SysOrg> page, @Param("entity") SysOrg entity);
}
