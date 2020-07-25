package com.zjmzxfzhl.modules.app.controller;

import com.zjmzxfzhl.common.core.Result;
import com.zjmzxfzhl.common.core.base.BaseController;
import com.zjmzxfzhl.common.core.redis.aspect.annotation.RepeatRequest;
import com.zjmzxfzhl.common.security.annotation.AnonymousAccess;
import com.zjmzxfzhl.modules.app.form.AppTransVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发示例Controller
 * 
 * 后端管理平台一般都是需要登陆的，所以 RepeatRequest 一般用于APP比较多
 * 
 * @author 庄金明
 */
@RestController
@RequestMapping("/app/demo")
@Slf4j
public class AppDemoController extends BaseController {

    @AnonymousAccess
    @GetMapping("/withoutLogin")
    public Result withoutLogin() {
        return Result.ok("不需要登陆，访问的交易，在Controller的Mapping方法使用@AnonymousAccess");
    }

    /**
     * 登陆用户请求防重发，若未获得锁等待2秒，若成功获得锁5秒后释放，并自定义提示信息
     * 
     * 未登陆用户直接访问，未设置防重发
     * 
     * @WithoutLogin 未登陆也可以访问，该项注释掉，则未登录用户无法访问
     * @return
     */
    @RepeatRequest(waitTime = 2, leaseTime = 5, msg = "等待2秒且5秒后释放")
    @GetMapping(value = "/repeatRequest1")
    public Result repeatRequest1() throws Exception {
        Thread.sleep(5000);
        log.info("请求接入repeatRequest1");
        return Result.ok();
    }

    /**
     * 登陆用户请求防重发，忽略 lockIndexs 设置
     * 
     * 未登陆用户根据设备序列号 deviceSn 和 设备IMEI号 deviceImei 组合防重发
     * 
     * @WithoutLogin 未登陆也可以访问
     * @param deviceSn
     * @param deviceImei
     * @return
     */
    @RepeatRequest(lockIndexs = { 0, 1 })
    @GetMapping(value = "/repeatRequest2")
    public Result repeatRequest2(@RequestParam String deviceSn, @RequestParam String deviceImei) throws Exception {
        Thread.sleep(5000);
        log.info("请求接入repeatRequest2");
        return Result.ok();
    }

    /**
     * 只有登录用户可以访问
     * 
     * 登录用户对相同 param 不可以同时访问，
     * 
     * 登录用户对不同 param 可以同时访问，
     * 
     * 一般使用场景：一个页面有多块区域需要求请求同一个URI，但参数不同，且页面是并行请求的
     * 
     * @param param
     * @return
     */
    @RepeatRequest(isLoginThenOnlyUserId = false, lockIndexs = 0)
    @GetMapping(value = "/repeatRequest3")
    public Result repeatRequest3(@RequestParam String param) throws Exception {
        Thread.sleep(5000);
        log.info("请求接入repeatRequest3");
        return Result.ok();
    }

    /**
     * 只有登录用户可以访问
     * 
     * 登录用户对相同 AppDemo.transId 不可以同时访问，
     * 
     * 登录用户对不同 AppDemo.transId 可以同时访问，
     * 
     * 一般使用场景：APP某一块功能全部使用该URI，但不同交易使用 transId 区分
     * 
     * @param appDemo
     * @return
     */
    @RepeatRequest(isLoginThenOnlyUserId = false, lockIndexs = 0, fieldNames = "transId")
    @GetMapping(value = "/repeatRequest4")
    public Result repeatRequest4(AppTransVO appDemo) throws Exception {
        Thread.sleep(1000);
        log.info("请求接入repeatRequest4");

        /// 具体业务逻辑处理begin
        /// String transId = appDemo.getTransId();
        /// String data = appDemo.getData();
        /// String serviceId = transId + "Service";// 假设 transId = t0001,则 serviceId = t0001Service,其中 t0001Service 是
        /// SomeBaseService子类的对象
        /// SomeBaseService someBaseService = SpringContextUtils.getBean(transId + "Service");
        /// someBaseService.exec(data);
        /// 具体业务逻辑处理end

        return Result.ok();
    }
}
