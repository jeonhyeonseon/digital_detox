package com.digitaldetox.digital_detox.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupRequestDto {

    private String nickname;
    private String password;
    private String email;

}
