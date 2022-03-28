package com.community.project.controller;

import com.community.project.dto.PostDto;
import com.community.project.dto.PostLikeDto;
import com.community.project.entity.Post;
import com.community.project.error.CreateException;
import com.community.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.community.project.error.ErrorCode.UNAUTHORIZED_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Collection<Post>> getPosts() {
        return ResponseEntity.ok().body(postService.getPosts());
    }

    @PostMapping("/post")
    public ResponseEntity<Post> savePost(
            @RequestHeader HttpHeaders header,
            @RequestBody @Valid PostDto postDto
    ) {
        if (header.get("Authorization") == null || header.get("Authorization").size() == 0) {
            throw new CreateException(UNAUTHORIZED_USER);
        }

        String str = String.valueOf(header.get("Authorization").get(0));
        String[] split = str.split("\\s+");

        Post post = new Post(false, postDto.getTitle(), postDto.getContent(), 0L);

        return ResponseEntity.ok().body(postService.savePost(Long.valueOf(split[1]), post));
    }

    @PostMapping("/post/count")
    public int updateViewCount(@RequestBody PostLikeDto postLikeDto) {
        return postService.updateViewCount(postLikeDto.getPostId());
    }

    @DeleteMapping("/post")
    public Boolean deletePost(
            @RequestHeader HttpHeaders header,
            @RequestBody PostLikeDto postLikeDto
    ) {
        // header check를 aop 분리 가능?
        if (header.get("Authorization") == null || header.get("Authorization").size() == 0) {
            throw new CreateException(UNAUTHORIZED_USER);
        }

        String str = String.valueOf(header.get("Authorization").get(0));
        String[] split = str.split("\\s+");

        return postService.deletePost(Long.valueOf(split[1]), postLikeDto.getPostId());
    }
}
