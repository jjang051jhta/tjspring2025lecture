package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {

    private final Member loggedMember;
    private Map<String, Object> oauth2UserInfo;
    public CustomUserDetails(Member loggedMember) {
        this.loggedMember = loggedMember;
    }
    public CustomUserDetails(Member loggedMember, Map<String, Object> oauth2UserInfo) {
        this.loggedMember = loggedMember;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //authorities.add((GrantedAuthority) () -> String.valueOf(loggedMember.getRole()));
        authorities.add(new SimpleGrantedAuthority(loggedMember.getRole().getLabel()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return loggedMember.getUserPW();
    }

    @Override
    public String getUsername() {
        return loggedMember.getUserID();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return oauth2UserInfo.get("name").toString();
    }
}
