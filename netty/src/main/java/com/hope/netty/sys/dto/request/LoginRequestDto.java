package com.hope.netty.sys.dto.request;

import lombok.*;

/**
 * Created by lijin on  2021/4/25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    private String username;

    private String password;

}
