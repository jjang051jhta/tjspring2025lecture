package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.ChangePasswordRequest;
import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.dto.LoginDto;
import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.service.MailService;
import com.jjang051.jpa.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;


    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }
    @PostMapping("/signup")
    public String signupProcess(@Valid @ModelAttribute("memberDto") MemberDto memberDto,
                                BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "member/signup";
        }
        memberService.save(memberDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "member/login";
    }
    @GetMapping("/info")
    public String info(Model model,
                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        model.addAttribute("loggedMember", customUserDetails.getLoggedMember());
        return "member/info";
    }
    @GetMapping("/mail")
    @ResponseBody
    public String mail() {
        mailService.sendHelperMail();
        return "mail send";
    }

    @GetMapping("/find-password")
    public String findPassword(Model model) {
        return "member/find-password";
    }

    @PostMapping("/find-password")
    public String findPasswordProcess(@RequestParam("userEmail") String userEmail) {
        mailService.sendChangePasswordMail(userEmail);
        return "redirect:/member/login";
    }
    @GetMapping("/find-id")
    public String findID(Model model) {
        return "member/find-id";
    }

    @PostMapping("/find-id")
    public String findIDProcess(@RequestParam("userEmail") String userEmail) {
        mailService.sendFindID(userEmail);
        return "redirect:/member/login";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        model.addAttribute("changePasswordRequest", new ChangePasswordRequest());
        return "member/change-password";
    }

    @PostMapping("/change-password")
    public String changePasswordProcess(@Valid @ModelAttribute("changePasswordRequest") ChangePasswordRequest changePasswordRequest,
                                        BindingResult bindingResult, Model model,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        HttpServletRequest request
                                        ) {
        //bindingResult.reject 는 필드에러 통과했는데 오류났을경우
        if(!changePasswordRequest.isConfirmed()) {
            bindingResult.rejectValue("newPasswordConfirm","mismatch","새 비밀번호가 일치하지 않습니다.");
        }
        String userID = customUserDetails.getLoggedMember().getUserID();
        String currentPassword = changePasswordRequest.getCurrentPassword();
        String newPassword = changePasswordRequest.getNewPassword();
        if(bindingResult.hasErrors()) {
            return "member/change-password";
        }
        try {
            memberService.resetPassword(userID, currentPassword, newPassword);
        } catch (IllegalArgumentException ex) {
            bindingResult.reject("changePWError",ex.getMessage());
            return "member/change-password";
        }
        request.getSession().invalidate(); // 세션 무효화
        SecurityContextHolder.clearContext(); // 인증정보 제거
        return "redirect:/member/login";
    }
}
