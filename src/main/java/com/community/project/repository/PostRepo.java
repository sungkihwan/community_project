package com.community.project.repository;

import com.community.project.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    @EntityGraph("JoinMember")
    List<Post> findAll();

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Post p SET p.view_count = p.view_count + 1 WHERE p.post_id = :post_id", nativeQuery = true)
    int updateViewCount(@Param("post_id") Long postId);
}
