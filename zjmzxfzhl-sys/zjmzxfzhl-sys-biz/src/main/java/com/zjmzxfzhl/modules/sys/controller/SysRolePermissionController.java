package com.zjmzxfzhl.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.modules.sys.entity.SysRolePermission;
import com.zjmzxfzhl.modules.sys.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 操作权限Controller
 * 
 * @author 庄金明
 */
@RestController
@RequestMapping("/sys/rolePermission")
public class SysRolePermissionController extends BaseController {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 自定义查询列表
     * 
     * @param sysRolePermission
     * @param current
     * @param size
     * @return
     */
    @PreAuthorize("@elp.single('sys:rolePermission:list')")
    @GetMapping(value = "/list")
    public Result list(SysRolePermission sysRolePermission, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysRolePermission> pageList = sysRolePermissionService.list(new Page<SysRolePermission>(current, size),
                sysRolePermission);
        return Result.ok(pageList);
    }

    @PreAuthorize("@elp.single('sys:rolePermission:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysRolePermission sysRolePermission = sysRolePermissionService.getById(id);
        return Result.ok(sysRolePermission);
    }

    /**
     * @功能：新增
     * @param sysRolePermission
     * @return
     */
    @PreAuthorize("@elp.single('sys:rolePermission:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysRolePermission sysRolePermission) {
        sysRolePermissionService.save(sysRolePermission);
        return Result.ok();
    }

    /**
     * @功能：修改
     * @param sysRolePermission
     * @return
     */
    @PreAuthorize("@elp.single('sys:rolePermission:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysRolePermission sysRolePermission) {
        sysRolePermissionService.updateById(sysRolePermission);
        return Result.ok();
    }

    /**
     * @功能：批量删除
     * @param ids
     * @return
     */
    @PreAuthorize("@elp.single('sys:rolePermission:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysRolePermissionService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysRolePermissionService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
