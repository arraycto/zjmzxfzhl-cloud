package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定时任务Mapper
 *
 * @author 庄金明
 */
public interface SysJobMapper extends BaseMapper<SysJob> {
    /**
     * 查询定时任务列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<SysJob> list(IPage<SysJob> page, @Param("entity") SysJob entity);
}
