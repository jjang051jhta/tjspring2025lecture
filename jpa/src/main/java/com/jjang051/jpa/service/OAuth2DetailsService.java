package com.jjang051.jpa.service;

import com.jjang051.jpa.constant.Role;
import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Member;
import com.jjang051.jpa.repository.MemberRepository;
import com.jjang051.jpa.social.GoogleUserInfo;
import com.jjang051.jpa.social.KakaoUserInfo;
import com.jjang051.jpa.social.SocialUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //social 로그인하면 여기로 들어온다.
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("userRequest={}", userRequest);
        Map<String, Object> oauth2UserInfo = (Map)oAuth2User.getAttributes();
        log.info("oauth2UserInfo={}", oauth2UserInfo);
        String provider  = userRequest.getClientRegistration().getRegistrationId();
        SocialUserInfo socialUserInfo = null;
        log.info("provider={}", provider);
        if(provider.equals("google")){
            log.info("provider=google");
            socialUserInfo = new GoogleUserInfo(oauth2UserInfo);
        } else if(provider.equals("kakao")){
            log.info("provider=kakao");
            socialUserInfo = new KakaoUserInfo(oauth2UserInfo);
        }
        Optional<Member> findedMember = memberRepository.findByUserID(socialUserInfo.getProviderID());
        Member returnedMember = null;
        if(findedMember.isPresent()){
            //최초 로그인하고 db에서 값을 끄집어 내는 경우
            returnedMember = findedMember.get();
        } else {
            //최초 로그인 시도해서 db에 값을 입력하고 되돌려 받은 경우
            Member member = Member.builder()
                    .userID(socialUserInfo.getProviderID())
                    .userEmail(socialUserInfo.getEmail())
                    .role(Role.ROLE_USER)
                    .userPW(bCryptPasswordEncoder.encode(UUID.randomUUID().toString()))
                    .build();
            returnedMember = memberRepository.save(member);
        }
        return new CustomUserDetails(returnedMember,oAuth2User.getAttributes());
    }
}
