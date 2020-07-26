package com.zjmzxfzhl.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.common.security.annotation.AnonymousAccess;
import com.zjmzxfzhl.modules.sys.entity.SysLog;
import com.zjmzxfzhl.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 系统日志Controller
 *
 * @author 庄金明
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 自定义查询列表
     *
     * @param sysLog
     * @param current
     * @param size
     * @return
     */
    @PreAuthorize("@elp.single('sys:log:list')")
    @GetMapping(value = "/list")
    public Result list(SysLog sysLog, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysLog> pageList = sysLogService.list(new Page<SysLog>(current, size), sysLog);
        return Result.ok(pageList);
    }

    @PreAuthorize("@elp.single('sys:log:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysLog sysLog = sysLogService.getById(id);
        return Result.ok(sysLog);
    }

    /**
     * @param sysLog
     * @return
     * @功能：新增
     */
    /// @PreAuthorize("@elp.single('sys:log:save')")
    @AnonymousAccess
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysLog sysLog) {
        sysLogService.save(sysLog);
        return Result.ok();
    }

    /**
     * @param sysLog
     * @return
     * @功能：修改
     */
    @PreAuthorize("@elp.single('sys:log:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysLog sysLog) {
        sysLogService.updateById(sysLog);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @PreAuthorize("@elp.single('sys:log:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysLogService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysLogService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
