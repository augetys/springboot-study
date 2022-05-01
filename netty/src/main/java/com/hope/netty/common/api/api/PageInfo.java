package com.hope.netty.common.api.api;

import lombok.Data;

/**
 * PageVO  用于分页
 * <p>
 * Created by lijin on  2021/6/8
 */
@Data
public class PageInfo {

    private Integer pageNum;

    private Integer pageSize;
}
