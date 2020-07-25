package com.zjmzxfzhl.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjmzxfzhl.modules.sys.entity.SysCodeType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 代码类别Mapper
 * 
 * @author 庄金明
 */
public interface SysCodeTypeMapper extends BaseMapper<SysCodeType> {
    /**
     * 查询代码类别列表
     * 
     * @param page
     * @param entity
     * @return
     */
    List<SysCodeType> list(IPage<SysCodeType> page, @Param("entity") SysCodeType entity);
}
