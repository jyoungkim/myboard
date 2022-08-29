package com.example.myboard.member.service;

import com.example.myboard.member.dto.MemberJoinDto;
import com.example.myboard.member.entity.Member;
import com.example.myboard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createMember(MemberJoinDto memberJoinDto) {
        Member member = new Member();
        member.setUsername(memberJoinDto.getUsername());
        member.setEmail(memberJoinDto.getEmail());
        String originPassword = memberJoinDto.getPassword();
        String newPassword = bCryptPasswordEncoder.encode(originPassword);
        member.setPassword(newPassword);

        memberRepository.save(member);
    }

    public Member getMember(String username) {
        Member getMember = memberRepository.findByUsername(username);
        if (!getMember.equals(null)) {
            return getMember;
        } else {
            throw new RuntimeException();
        }
    }
}
