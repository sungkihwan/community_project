package com.community.project.controller;

import com.community.project.entity.PostLike;
import com.community.project.service.PostLikeService;
import com.community.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @GetMapping("/postlikes")
    public ResponseEntity<Collection<PostLike>> getPostLikes() {
        return ResponseEntity.ok().body(postLikeService.getPostLikes());
    }

    @GetMapping("/postlikes/{postId}")
    public ResponseEntity<Collection<PostLike>> getPostLikesByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postLikeService.getPostLikesByPostId(postId));
    }

    @PostMapping("/postlike")
    public PostLike savePostLike(@RequestBody PostLike postLike) {
        return postLikeService.savePostLike(postLike);
    }
}
