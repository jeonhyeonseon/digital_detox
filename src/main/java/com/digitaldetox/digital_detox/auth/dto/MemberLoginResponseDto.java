package com.digitaldetox.digital_detox.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginResponseDto {

    private Long memberId;
    private String role;
    private String email;
    private String password;

}
