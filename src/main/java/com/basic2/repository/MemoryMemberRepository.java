package com.basic2.repository;

import com.basic2.model.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    static Long memberId = 0L;
    Map<Long, Member> members = new HashMap<>();

    @Override
    public void save(Member member) {
        member.setId(++memberId);
        members.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findByname(String name) {
        return members.values().stream()
                .filter(m -> m.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

}
