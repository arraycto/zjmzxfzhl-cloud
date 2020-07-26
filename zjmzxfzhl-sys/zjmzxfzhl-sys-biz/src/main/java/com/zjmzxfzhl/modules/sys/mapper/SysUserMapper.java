package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.common.core.aspect.annotation.DataPermission;
import com.zjmzxfzhl.modules.sys.entity.SysMenu;
import com.zjmzxfzhl.modules.sys.entity.SysRole;
import com.zjmzxfzhl.modules.sys.entity.SysUser;
import com.zjmzxfzhl.modules.sys.entity.vo.SysRolePermissionVO;
import com.zjmzxfzhl.modules.sys.permission.provider.OrgDataPermissionProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author 庄金明
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 查询用户列表
     *
     * @param page
     * @param entity
     * @return
     */
    @DataPermission(providers = OrgDataPermissionProvider.class, providerParams = "{\"alias\":\"o\",\"type\":\"1\"}",
            fieldName = "authFilterSql")
    List<SysUser> list(IPage<SysUser> page, @Param("entity") SysUser entity);

    /**
     * 根据用户Id查询角色列表
     *
     * @param userId
     * @return
     */
    List<SysRole> getRolesByUserId(@Param("userId") String userId);

    /**
     * 查询所有权限清单
     *
     * @return
     */
    List<SysRolePermissionVO> getAdminPermissions();

    /**
     * 根据角色Id查询角色权限清单
     *
     * @param roleId
     * @return
     */
    List<SysRolePermissionVO> getRolePermissions(@Param("roleId") String roleId);

    /**
     * 根据角色Id查询菜单列表
     *
     * @param roleId
     * @return
     */
    List<SysMenu> getRoleMenus(@Param("roleId") String roleId);
}
