package com.community.project.controller;

import com.community.project.entity.Post;
import com.community.project.entity.PostLike;
import com.community.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Collection<Post>> getPosts() {
        return ResponseEntity.ok().body(postService.getPosts());
    }

    @PostMapping("/post/count")
    public int updateViewCount(@RequestParam Long postId) {
        return postService.updateViewCount(postId);
    }

    @DeleteMapping("/post")
    public Boolean deletePost(@RequestParam Long postId) {
        return postService.deletePost(postId);
    }
}
