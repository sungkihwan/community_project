package com.community.project.repository;

import com.community.project.entity.Post;
import com.community.project.entity.PostLike;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostLikeRepo extends JpaRepository<PostLike, Long> {

    @EntityGraph("JoinMemberAndPostWithLike")
    List<PostLike> findAll();

    @Query(value = "select * from post_like l join member m on l.member_id = m.member_id " +
            "join post p on p.post_id = l.post_id where p.post_id = :post_id", nativeQuery = true)
    List<PostLike> findPostLikesByPostId(@Param("post_id") Long id);

    @Query(value = "select * from post_like l join member m on l.member_id = m.member_id" +
            " join post p on p.post_id = l.post_id where m.member_id = :member_id", nativeQuery = true)
    List<PostLike> findPostLikesByMemberId(@Param("member_id") Long id);
}
