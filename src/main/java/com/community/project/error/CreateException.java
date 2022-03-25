package com.community.project.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateException extends RuntimeException {
    private final ErrorCode errorCode;
}
