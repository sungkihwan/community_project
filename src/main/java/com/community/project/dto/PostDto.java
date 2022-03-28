package com.community.project.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PostDto {
    @NotNull
    private String title;
    @NotNull
    private String content;
}
