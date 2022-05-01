package com.hope.netty.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hope.netty.sys.dto.request.LoginRequestDto;
import com.hope.netty.sys.model.SysUser;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author lijin
 * @since 2021-04-25
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 登录
     *
     * @param loginRequestDto
     * @return
     */
    SysUser login(LoginRequestDto loginRequestDto, HttpSession session);

    SysUser getUserBySession(HttpSession session);

    SysUser getUserByUserId(String userId);

    boolean logout(HttpSession session);
}
