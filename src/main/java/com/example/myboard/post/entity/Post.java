package com.example.myboard.post.entity;

import com.example.myboard.member.entity.Member;
import com.example.myboard.reply.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}
