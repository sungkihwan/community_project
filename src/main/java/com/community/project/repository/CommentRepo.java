package com.community.project.repository;

import com.community.project.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    @EntityGraph("JoinMemberAndPost")
    List<Comment> findAll();

    @EntityGraph("JoinMemberAndPost")
    List<Comment> findByPostId(Long id);

    @EntityGraph("JoinMemberAndPost")
    List<Comment> findByMemberId(Long id);
}
