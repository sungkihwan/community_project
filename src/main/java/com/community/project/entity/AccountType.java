package com.community.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountType {

    Realtor("공인중개사"),
    Lessee("임차인"),
    Lessor("임대인");

    private final String detail;
}
