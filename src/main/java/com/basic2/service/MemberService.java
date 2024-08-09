package com.basic2.service;

import com.basic2.model.Member;
import com.basic2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> findByName(String name) {
        return memberRepository.findByname(name);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
