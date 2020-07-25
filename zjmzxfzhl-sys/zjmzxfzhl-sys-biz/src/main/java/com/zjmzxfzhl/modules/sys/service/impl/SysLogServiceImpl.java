package com.zjmzxfzhl.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.common.core.base.BaseServiceImpl;
import com.zjmzxfzhl.modules.sys.entity.SysLog;
import com.zjmzxfzhl.modules.sys.mapper.SysLogMapper;
import com.zjmzxfzhl.modules.sys.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * 系统日志Service
 *
 * @author 庄金明
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    @Override
    public IPage<SysLog> list(IPage<SysLog> page, SysLog sysLog) {
        return page.setRecords(baseMapper.list(page, sysLog));
    }
}
