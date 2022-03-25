package com.community.project.repository;

import com.community.project.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepo extends JpaRepository<PostLike, Long> {
    List<PostLike> findAllByPostId(Long postId);
}
