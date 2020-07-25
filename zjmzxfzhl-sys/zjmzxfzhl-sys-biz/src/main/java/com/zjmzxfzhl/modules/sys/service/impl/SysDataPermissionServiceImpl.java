package com.zjmzxfzhl.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.common.core.base.BaseServiceImpl;
import com.zjmzxfzhl.modules.sys.entity.SysDataPermission;
import com.zjmzxfzhl.modules.sys.mapper.SysDataPermissionMapper;
import com.zjmzxfzhl.modules.sys.service.SysDataPermissionService;
import org.springframework.stereotype.Service;

/**
 * 数据权限Service
 * 
 * @author 庄金明
 */
@Service
public class SysDataPermissionServiceImpl extends BaseServiceImpl<SysDataPermissionMapper, SysDataPermission>
        implements SysDataPermissionService {
    @Override
    public IPage<SysDataPermission> list(IPage<SysDataPermission> page, SysDataPermission sysDataPermission) {
        return page.setRecords(baseMapper.list(page, sysDataPermission));
    }
}
