package com.example.myboard.member.repository;

import com.example.myboard.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByEmail(String email);

    public Member findByUsername(String username);
}
