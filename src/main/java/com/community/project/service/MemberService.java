package com.community.project.service;

import com.community.project.entity.Member;
import com.community.project.error.CreateException;
import com.community.project.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.community.project.error.ErrorCode.USER_NOT_FOUND;

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

    public Member getMember(Long id) {
        Optional<Member> member = memberRepo.findById(id);
        if (!member.isPresent()) throw new CreateException(USER_NOT_FOUND);
        return member.get();
    }

    public List<Member> findAll() {
        return memberRepo.findAll();
    }
}
