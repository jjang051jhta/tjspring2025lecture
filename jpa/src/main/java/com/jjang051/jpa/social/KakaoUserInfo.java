package com.jjang051.jpa.social;

public class KakaoUserInfo implements SocialUserInfo {

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
