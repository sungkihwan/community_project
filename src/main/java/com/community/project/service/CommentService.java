package com.community.project.service;

import com.community.project.dto.CommentDto;
import com.community.project.entity.Comment;
import com.community.project.entity.Member;
import com.community.project.entity.Post;
import com.community.project.repository.CommentRepo;
import com.community.project.repository.MemberRepo;
import com.community.project.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {
    private final CommentRepo commentRepo;
    private final MemberRepo memberRepo;
    private final PostRepo postRepo;

    public List<Comment> getComments(Long id) {
        return commentRepo.findByPostId(id);
    }

    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public void saveComment(Long memberId, CommentDto commentDto) {
        Member member = memberRepo.getById(memberId);
        Post post = postRepo.getById(commentDto.getPostId());
        Comment newComment = new Comment(commentDto.getComment(), post, member);
        commentRepo.save(newComment);
    }
}
