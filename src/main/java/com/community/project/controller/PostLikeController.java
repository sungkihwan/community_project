package com.community.project.controller;

import com.community.project.dto.PostLikeDto;
import com.community.project.entity.PostLike;
import com.community.project.entity.Member;
import com.community.project.entity.Post;
import com.community.project.error.CreateException;
import com.community.project.service.PostLikeService;
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
public class PostLikeController {

    private final PostLikeService postLikeService;

    @GetMapping("/postlikes")
    public ResponseEntity<Collection<PostLike>> getPostLikes() {
        return ResponseEntity.ok().body(postLikeService.getPostLikes());
    }

    @GetMapping("/postlikes/post/{id}")
    public ResponseEntity<Collection<PostLike>> getPostLikesByPostId(@PathVariable Long id) {
        return ResponseEntity.ok().body(postLikeService.getLikesByPostId(id));
    }

    @GetMapping("/postlikes/member/{id}")
    public ResponseEntity<Collection<PostLike>> getPostLikesByMemberId(@PathVariable Long id) {
        return ResponseEntity.ok().body(postLikeService.getLikesByMemberId(id));
    }

    @PostMapping("/postlike")
    public void savePostLike(
            @RequestHeader HttpHeaders header,
            @RequestBody @Valid PostLikeDto postLikeDto
    ) {
        if (header.get("Authorization") == null || header.get("Authorization").size() == 0) {
            throw new CreateException(UNAUTHORIZED_USER);
        }

        String str = String.valueOf(header.get("Authorization").get(0));
        String[] split = str.split("\\s+");

        postLikeService.savePostLike(Long.valueOf(split[1]), postLikeDto.getPostId());
    }
}
