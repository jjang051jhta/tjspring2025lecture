package com.jjang051.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoUserInfo implements SocialUserInfo {
    private final Map<String, Object> attributes;
    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderID() {
        return "kakao_";
    }
}
