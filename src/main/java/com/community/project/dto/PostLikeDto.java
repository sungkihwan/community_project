package com.community.project.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PostLikeDto {
    @NotNull
    private Long postId;
}
