package com.digitaldetox.digital_detox.auth.controller;

import com.digitaldetox.digital_detox.auth.dto.MemberSignupRequestDto;
import com.digitaldetox.digital_detox.auth.dto.MemberSignupResponseDto;
import com.digitaldetox.digital_detox.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberSignupResponseDto> signup(@RequestBody MemberSignupRequestDto memberSignupRequestDto) {

        Long memberId = authService.signup(memberSignupRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberSignupResponseDto(memberId));
    }
}
