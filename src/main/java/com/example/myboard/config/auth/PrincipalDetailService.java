package com.example.myboard.config.auth;

import com.example.myboard.member.entity.Member;
import com.example.myboard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("매개변수로 받아지는 값이 있는가? : " + username)  ;

        Member member = memberRepository.findByEmail(username);
        if (member != null) {
            return new PrincipalDetails(member);
        }
        return null;
    }
}
