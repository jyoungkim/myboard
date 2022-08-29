package com.example.myboard.post.service;


import com.example.myboard.post.entity.Post;
import com.example.myboard.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Page<Post> getPosts(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size, Sort.by("postId").descending()));
    }

}
