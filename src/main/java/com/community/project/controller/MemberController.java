package com.community.project.controller;

import com.community.project.dto.MemberDto;
import com.community.project.dto.PostLikeDto;
import com.community.project.entity.Member;
import com.community.project.error.CreateException;
import com.community.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;

import static com.community.project.error.ErrorCode.TYPE_NOT_FOUND;
import static com.community.project.error.ErrorCode.UNAUTHORIZED_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<Collection<Member>> getMembers() {
        return ResponseEntity.ok().body(memberService.findAll());
    }

    @GetMapping("/member")
    public ResponseEntity<Member> getMember(@RequestHeader HttpHeaders header) {
        String str = String.valueOf(header.get("Authorization").get(0));
        if (str == null || str.isEmpty()) {
            throw new CreateException(UNAUTHORIZED_USER);
        }

        System.out.println("str = " + str);

        String[] split = str.split("\\s+");
        return ResponseEntity.ok().body(memberService.getMember(Long.valueOf(split[1])));
    }

    @PostMapping("/member")
    public ResponseEntity<Member> saveMember(
            @RequestBody @Valid MemberDto memberDto
    ) {
        String type = memberDto.getAccountType();
        String newType = null;
        if (Objects.equals(type, "Realtor")) {
            newType = "공인중개사";
        } else if (Objects.equals(type, "Lessor")) {
            newType = "임대인";
        } else if (Objects.equals(type, "Lessee")) {
            newType = "임차인";
        }

        if (newType == null) throw new CreateException(TYPE_NOT_FOUND);

        Member member = new Member(memberDto.getNickname(), newType, false);
        return ResponseEntity.ok().body(memberService.saveMember(member));
    }

    @DeleteMapping("/member")
    public Boolean deleteMember(
            @RequestHeader HttpHeaders header
    ) {
        if (header.get("Authorization") == null || header.get("Authorization").size() == 0) {
            throw new CreateException(UNAUTHORIZED_USER);
        }

        String str = String.valueOf(header.get("Authorization").get(0));
        String[] split = str.split("\\s+");

        return memberService.deleteMember(Long.valueOf(split[1]));
    }
}
