package com.example.myboard.post.controller;


import com.example.myboard.member.entity.Member;
import com.example.myboard.member.service.MemberService;
import com.example.myboard.post.entity.Post;
import com.example.myboard.post.repository.PostRepository;
import com.example.myboard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberService memberService;

//    @GetMapping("/")
//    public String index(Model model) {
//        List<Post> postList = postService.getPosts();
//        model.addAttribute("postList", postList);
//
//        return "index";
//    }

    @GetMapping("/")
    public String postWithPage(Model model,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Post> postList = postService.getPosts(page - 1, size);
        //List<Post> postList = pagePostList.getContent();
        model.addAttribute("postList", postList);

        return "index";
    }

    @GetMapping("/post/{postId}")
    public String getDetailPost(Model model, @PathVariable("postId") long postId) {
        Optional<Post> detailPost = postRepository.findById(postId);
        Post post = detailPost.get();
        model.addAttribute("post", post);

        return "detail_post";
    }

    @GetMapping("/post")
    public String getPost(Post post) {
        return "post";
    }

    @GetMapping("/post/modify/{postId}")
    public String getModify(Model model, @PathVariable("postId") long postId) {
        Optional<Post> detailPost = postRepository.findById(postId);
        Post post = detailPost.get();
        model.addAttribute("post", post);

        return "modify";
    }

    @PatchMapping("/post/modify/{postId}")
    public String doModify(Model model, Post post, @PathVariable("postId") long postId) {
        System.out.println("패치 메소드 실행 되었는가? ");
        Optional<Post> detailPost = postRepository.findById(postId);
        Post findPost = detailPost.get();

        findPost.setTitle(post.getTitle());
        findPost.setContent(post.getContent());

        postRepository.save(findPost);

        model.addAttribute("post", post);
        return String.format("redirect:/post/%s", postId);
    }

//    @GetMapping("/post")
//    public String getPost(Model model) {
//        model.addAttribute("post", new Post());
//        return "post";
//    }

    @PostMapping("/post")
    public String createPost(Post post, Principal principal) {
        //model.addAttribute("post", post);

        Member getMember = memberService.getMember(principal.getName());
        post.setMember(getMember);

        postRepository.save(post);

        return "redirect:/";
    }

    //    @PostMapping("/post")
//    public String createPost(@ModelAttribute Model model, Post post) {
//
//        model.addAttribute("post", new Post());
//        postRepository.save(post);
//        return "redirect:/";
//    }

    @DeleteMapping("/post/{postId}")
    public String deletePost(Principal principal, @PathVariable("postId") long postId) {
        System.out.println("여기 지금 들어가고 있는가? ");

        postRepository.deleteById(postId);
        return "redirect:/";
    }
}

// TODO: 작성한 글을 수정/삭제하는 기능 추가 구현 필요

