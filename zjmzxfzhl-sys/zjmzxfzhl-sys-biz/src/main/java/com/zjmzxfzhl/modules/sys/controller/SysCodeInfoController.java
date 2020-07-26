package com.zjmzxfzhl.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.common.log.annotation.Log;
import com.zjmzxfzhl.modules.sys.entity.SysCodeInfo;
import com.zjmzxfzhl.modules.sys.service.SysCodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 代码信息Controller
 *
 * @author 庄金明
 */
@RestController
@RequestMapping("/sys/codeInfo")
public class SysCodeInfoController extends BaseController {
    @Autowired
    private SysCodeInfoService sysCodeInfoService;

    /**
     * 自定义查询列表
     *
     * @param sysCodeInfo
     * @param current
     * @param size
     * @return
     */
    @PreAuthorize("@elp.single('sys:codeInfo:list')")
    @GetMapping(value = "/list")
    public Result list(SysCodeInfo sysCodeInfo, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysCodeInfo> pageList = sysCodeInfoService.list(new Page<SysCodeInfo>(current, size), sysCodeInfo);
        return Result.ok(pageList);
    }

    @PreAuthorize("@elp.single('sys:codeInfo:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysCodeInfo sysCodeInfo = sysCodeInfoService.getById(id);
        return Result.ok(sysCodeInfo);
    }

    /**
     * @param sysCodeInfo
     * @return
     * @功能：新增
     */
    @Log(value = "新增代码信息")
    @PreAuthorize("@elp.single('sys:codeInfo:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysCodeInfo sysCodeInfo) {
        sysCodeInfoService.saveSysCodeInfo(sysCodeInfo);
        return Result.ok();
    }

    /**
     * @param sysCodeInfo
     * @return
     * @功能：修改
     */
    @Log(value = "修改代码信息")
    @PreAuthorize("@elp.single('sys:codeInfo:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysCodeInfo sysCodeInfo) {
        sysCodeInfoService.updateSysCodeInfo(sysCodeInfo);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @Log(value = "删除代码信息")
    @PreAuthorize("@elp.single('sys:codeInfo:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        sysCodeInfoService.deleteSysCodeInfo(ids);
        return Result.ok();
    }

    /**
     * 根据代码类型查询代码信息清单
     *
     * @param codeTypeIds
     * @return
     */
    @GetMapping(value = "/getSysCodeInfos")
    public Result getSysCodeInfos(String codeTypeIds) {
        Map<String, List<SysCodeInfo>> sysCodeInfosMap = sysCodeInfoService.getSysCodeInfosFromRedis(codeTypeIds);
        if (sysCodeInfosMap == null) {
            sysCodeInfosMap = sysCodeInfoService.getSysCodeInfosFromDb(codeTypeIds);
        }
        return Result.ok(sysCodeInfosMap);
    }
}
