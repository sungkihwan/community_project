package com.community.project.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String nickname;
    private String accountType;

    private Boolean quit;

    public Member(String nickname, String accountType, Boolean quit) {
        this.nickname = nickname;
        this.accountType = accountType;
        this.quit = quit;
    }
}
