package com.hope.netty.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.netty.common.constant.CommonConstant;
import com.hope.netty.common.exception.BusinessException;
import com.hope.netty.sys.dto.request.LoginRequestDto;
import com.hope.netty.sys.mapper.SysUserMapper;
import com.hope.netty.sys.model.SysUser;
import com.hope.netty.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.MessageFormat;


/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-04-25
 */
@Service
@Transactional
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser login(LoginRequestDto loginRequestDto, HttpSession session) {
        // 校验用户名是否存在
        SysUser sysUser = findUserByUserName(loginRequestDto.getUsername());
        if (sysUser == null) {
            throw new BusinessException("该用户不存在!");
        }
        // 密码是否正确
        if (!loginRequestDto.getPassword().equals(sysUser.getPassword())) {
            throw new BusinessException("密码不正确!");
        }
        session.setAttribute(CommonConstant.USER_TOKEN, sysUser.getId());
        return sysUser;
    }

    @Override
    public SysUser getUserBySession(HttpSession session) {
        String username = (String) session.getAttribute(CommonConstant.USER_TOKEN);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id", username);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public SysUser getUserByUserId(String userId) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userId);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public boolean logout(HttpSession session) {
        Object userId = session.getAttribute(CommonConstant.USER_TOKEN);
        if (userId == null) {
            throw new BusinessException("请先登录！");
        }
        session.removeAttribute(CommonConstant.USER_TOKEN);
        log.info(MessageFormat.format("userId为 {0} 的用户已注销登录!", userId));
        return true;
    }


    public SysUser findUserByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return sysUserMapper.selectOne(wrapper);
    }

}
