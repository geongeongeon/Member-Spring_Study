package com.basic2.repository;

import com.basic2.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);
    Optional<Member> findByname(String name);
    List<Member> findAll();

}
