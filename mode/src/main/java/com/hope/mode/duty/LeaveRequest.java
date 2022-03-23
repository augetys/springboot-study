package com.hope.mode.duty;

import lombok.*;

/**
 * 请假请求
 * Created by lijin on  2022/3/11
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {
    /**天数*/
    private int leaveDays;

    /**姓名*/
    private String name;
}
