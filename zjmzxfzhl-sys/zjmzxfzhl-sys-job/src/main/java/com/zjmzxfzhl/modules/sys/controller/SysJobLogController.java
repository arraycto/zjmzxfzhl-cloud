package com.zjmzxfzhl.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.modules.sys.entity.SysJobLog;
import com.zjmzxfzhl.modules.sys.service.SysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 定时任务执行日志Controller
 *
 * @author 庄金明
 */
@RestController
@RequestMapping("/sys/jobLog")
public class SysJobLogController extends BaseController {
    @Autowired
    private SysJobLogService sysJobLogService;

    /**
     * 自定义查询列表
     *
     * @param sysJobLog
     * @param current
     * @param size
     * @return
     */
    @PreAuthorize("@elp.single('sys:jobLog:list')")
    @GetMapping(value = "/list")
    public Result list(SysJobLog sysJobLog, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysJobLog> pageList = sysJobLogService.list(new Page<SysJobLog>(current, size), sysJobLog);
        return Result.ok(pageList);
    }

    @PreAuthorize("@elp.single('sys:jobLog:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysJobLog sysJobLog = sysJobLogService.getById(id);
        return Result.ok(sysJobLog);
    }

    /**
     * @param sysJobLog
     * @return
     * @功能：新增
     */
    @PreAuthorize("@elp.single('sys:jobLog:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysJobLog sysJobLog) {
        sysJobLogService.save(sysJobLog);
        return Result.ok();
    }

    /**
     * @param sysJobLog
     * @return
     * @功能：修改
     */
    @PreAuthorize("@elp.single('sys:jobLog:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysJobLog sysJobLog) {
        sysJobLogService.updateById(sysJobLog);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @PreAuthorize("@elp.single('sys:jobLog:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysJobLogService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysJobLogService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
