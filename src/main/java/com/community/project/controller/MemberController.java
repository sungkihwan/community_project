package com.community.project.controller;

import com.community.project.entity.Member;
import com.community.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<Collection<Member>> getMembers() {
        return ResponseEntity.ok().body(memberService.findAll());
    }
}
