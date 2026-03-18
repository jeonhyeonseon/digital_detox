package com.digitaldetox.digital_detox.auth.service;

import com.digitaldetox.digital_detox.auth.dto.MemberSignupRequestDto;
import com.digitaldetox.digital_detox.member.entity.Member;
import com.digitaldetox.digital_detox.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long signup(MemberSignupRequestDto memberSignupRequestDto) {

        if (memberRepository.existsByEmail(memberSignupRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다. 다시 입력해주세요.");
        }

        Member member = Member.builder()
                .nickname(memberSignupRequestDto.getNickname())
                .password(passwordEncoder.encode(memberSignupRequestDto.getPassword()))
                .email(memberSignupRequestDto.getEmail())
                .build();

        Member savedMember = memberRepository.save(member);

        return savedMember.getMemberId();
    }
}
