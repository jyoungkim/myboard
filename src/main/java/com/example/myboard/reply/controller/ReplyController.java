package com.example.myboard.reply.controller;

import com.example.myboard.post.entity.Post;
import com.example.myboard.post.repository.PostRepository;
import com.example.myboard.reply.entity.Reply;
import com.example.myboard.reply.respository.ReplyRepository;
import com.example.myboard.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ReplyController {

    private final ReplyRepository replyRepository;
    private final ReplyService replyService;
    private final PostRepository postRepository;

    public ReplyController(ReplyRepository replyRepository, ReplyService replyService, PostRepository postRepository) {
        this.replyRepository = replyRepository;
        this.replyService = replyService;
        this.postRepository = postRepository;
    }

    @PostMapping("/post/{postId}")
    public String createReply(Reply reply, @PathVariable("postId") long postId) {
        Optional<Post> findPost = postRepository.findById(postId);
        reply.setPost(findPost.get());

        replyRepository.save(reply);

        return String.format("redirect:/post/%s", postId);
    }


}
