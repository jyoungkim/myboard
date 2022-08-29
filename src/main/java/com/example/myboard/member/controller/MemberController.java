package com.example.myboard.member.controller;

import com.example.myboard.member.dto.MemberJoinDto;
import com.example.myboard.member.entity.Member;
import com.example.myboard.member.repository.MemberRepository;
import com.example.myboard.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String getJoin(MemberJoinDto memberJoinDto) {
        return "join";
    }

    @PostMapping("/join")
    public String join(MemberJoinDto memberJoinDto, BindingResult bindingResult) {

        if (!memberJoinDto.getPassword().equals(memberJoinDto.getPasswordCheck())) {
            bindingResult.rejectValue("passwordCheck", "passwordIncorrect", "패스워드가 일치하지 않습니다.");

            return "/join";
        }
        memberService.createMember(memberJoinDto);

        return "redirect:join_completed";
    }

    @GetMapping("/join_completed")
    public String joinCompleted() {
        return "join_completed";
    }
}
