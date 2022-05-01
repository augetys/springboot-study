package com.hope.netty.sys.controller;


import com.hope.netty.common.api.api.CommonResult;
import com.hope.netty.sys.dto.request.LoginRequestDto;
import com.hope.netty.sys.model.SysUser;
import com.hope.netty.sys.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserService iSysUserService;

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    public CommonResult<SysUser> login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        SysUser sysUser = iSysUserService.login(loginRequestDto, session);
        return CommonResult.success(sysUser);
    }


    /**
     * 获取用户信息
     */
    @GetMapping(value = "/getUserInfo")
    public CommonResult<SysUser> login(HttpSession session) {
        SysUser sysUser = iSysUserService.getUserBySession(session);
        return CommonResult.success(sysUser);
    }

    /**
     * 获取用户信息
     */
    @GetMapping(value = "/logout")
    public CommonResult<Boolean> logout(HttpSession session) {
        boolean flag = iSysUserService.logout(session);
        return CommonResult.success(flag);
    }
}

