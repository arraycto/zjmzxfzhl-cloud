package com.zjmzxfzhl.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.common.log.annotation.Log;
import com.zjmzxfzhl.modules.sys.entity.SysConfig;
import com.zjmzxfzhl.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统参数Controller
 * 
 * @author 庄金明
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends BaseController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 自定义查询列表
     * 
     * @param sysConfig
     * @param current
     * @param size
     * @return
     */
    @PreAuthorize("@elp.single('sys:config:list')")
    @GetMapping(value = "/list")
    public Result list(SysConfig sysConfig, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysConfig> pageList = sysConfigService.list(new Page<SysConfig>(current, size), sysConfig);
        return Result.ok(pageList);
    }

    @PreAuthorize("@elp.single('sys:config:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysConfig sysConfig = sysConfigService.getById(id);
        return Result.ok(sysConfig);
    }

    /**
     * @功能：新增
     * @param sysConfig
     * @return
     */
    @Log(value = "新增系统参数")
    @PreAuthorize("@elp.single('sys:config:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysConfig sysConfig) {
        sysConfigService.saveSysConfig(sysConfig);
        return Result.ok();
    }

    /**
     * @功能：修改
     * @param sysConfig
     * @return
     */
    @Log(value = "修改系统参数")
    @PreAuthorize("@elp.single('sys:config:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysConfig sysConfig) {
        sysConfigService.updateSysConfig(sysConfig);
        return Result.ok();
    }

    /**
     * @功能：批量删除
     * @param ids
     * @return
     */
    @Log(value = "删除系统参数")
    @PreAuthorize("@elp.single('sys:config:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        sysConfigService.deleteSysConfig(ids);
        return Result.ok();
    }
}
