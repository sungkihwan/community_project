package com.community.project.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MemberDto {
    @NotNull
    private String nickname;
    @NotNull
    private String accountType;
}
