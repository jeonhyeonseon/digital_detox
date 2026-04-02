package com.digitaldetox.digital_detox.auth.controller;

import com.digitaldetox.digital_detox.auth.dto.MemberLoginRequestDto;
import com.digitaldetox.digital_detox.auth.dto.MemberLoginResponseDto;
import com.digitaldetox.digital_detox.auth.dto.MemberSignupRequestDto;
import com.digitaldetox.digital_detox.auth.dto.MemberSignupResponseDto;
import com.digitaldetox.digital_detox.auth.service.AuthService;
import com.digitaldetox.digital_detox.member.domain.Member;
import com.digitaldetox.digital_detox.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/signup")
    public ResponseEntity<MemberSignupResponseDto> signup(@Valid @RequestBody MemberSignupRequestDto memberSignupRequestDto) {

        Long memberId = authService.signup(memberSignupRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberSignupResponseDto(memberId));
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto loginRequestDto,
                                                        HttpServletRequest httpServletRequest,
                                                        HttpServletResponse httpServletResponse) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        securityContextRepository.saveContext(securityContext, httpServletRequest, httpServletResponse);

        Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 정보입니다. 다시 입력해주세요"));

        MemberLoginResponseDto loginResponseDto = new MemberLoginResponseDto(
                member.getMemberId(),
                member.getRole().name(),
                member.getEmail(),
                member.getNickname()
        );

        return ResponseEntity.ok(loginResponseDto);
    }
}
