package com.basic2;

import com.basic2.repository.MemberRepository;
import com.basic2.repository.MemoryMemberRepository;
import com.basic2.service.MemberService;
import org.springframework.context.annotation.Bean;

public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
