package com.community.project.controller;

import com.community.project.dto.CommentDto;
import com.community.project.entity.Comment;
import com.community.project.error.CreateException;
import com.community.project.service.CommentService;
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
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment/post/{id}")
    public ResponseEntity<Collection<Comment>> getCommentsByPostId(@PathVariable Long id) {
        return ResponseEntity.ok().body(commentService.getCommentsByPostId(id));
    }

    @GetMapping("/comment/member/{id}")
    public ResponseEntity<Collection<Comment>> getCommentsByMemberId(@PathVariable Long id) {
        return ResponseEntity.ok().body(commentService.getCommentsByMemberId(id));
    }

    @GetMapping("/comments")
    public ResponseEntity<Collection<Comment>> getPosts() {
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @PostMapping("/comment")
    public void saveComment(
            @RequestHeader HttpHeaders header,
            @RequestBody @Valid CommentDto commentDto
    ) {
        if (header.get("Authorization") == null || header.get("Authorization").size() == 0) {
            throw new CreateException(UNAUTHORIZED_USER);
        }

        String str = String.valueOf(header.get("Authorization").get(0));
        String[] split = str.split("\\s+");

        commentService.saveComment(Long.valueOf(split[1]), commentDto);
    }
}
