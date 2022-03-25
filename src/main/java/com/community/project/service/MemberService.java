package com.community.project.service;

import com.community.project.entity.Member;
import com.community.project.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional @Slf4j
@Service
public class MemberService {
    private final MemberRepo memberRepo;

    public Member saveMember(Member member) {
        return memberRepo.save(member);
    }

    public Optional<Member> findById(Long id) {
        return memberRepo.findById(id);
    }

    public List<Member> findAll() {
        return memberRepo.findAll();
    }
}
