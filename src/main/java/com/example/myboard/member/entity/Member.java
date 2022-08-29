package com.example.myboard.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole = MemberRole.ROLE_USER;

    @Column(unique = true)
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    public enum MemberRole {
        ROLE_USER,
        ROLE_ADMIN
    }
}
