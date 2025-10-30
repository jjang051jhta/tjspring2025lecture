package com.jjang051.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GoogleUserInfo implements SocialUserInfo {
    private final Map<String, Object> attributes;

    @Override
    public String getName() {
        //userName
        return (String)attributes.get("name");
    }

    @Override
    public String getEmail() {
        //userEmail
        return attributes.get("email").toString();
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderID() {
        //이게 userID
        //google_101090519367370540981
        return getProvider()+"_"+attributes.get("sub").toString();
    }
}
