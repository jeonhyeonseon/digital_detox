package com.digitaldetox.digital_detox.member.repository;

import com.digitaldetox.digital_detox.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
