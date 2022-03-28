package com.community.project.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CommentDto {

    @NotNull
    private String comment;
    @NotNull
    private Long postId;
}
