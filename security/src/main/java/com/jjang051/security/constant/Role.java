package com.jjang051.security.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_ADMIN("최고관리자",10),
    ROLE_MANAGER("매니저",3),
    ROLE_USER("일반회원",1);
    private final String role;
    private final int level;
}
