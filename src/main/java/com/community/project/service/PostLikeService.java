package com.community.project.service;

import com.community.project.entity.PostLike;
import com.community.project.repository.PostLikeRepo;
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
public class PostLikeService {

    private final PostRepo postRepo;
    private final PostLikeRepo postLikeRepo;

    public PostLike savePostLike(PostLike postLike) {
        return postLikeRepo.save(postLike);
    }

    public List<PostLike> getPostLikes() {
        return postLikeRepo.findAll();
    }

    public List<PostLike> getPostLikesByPostId(Long postId) {
        return postLikeRepo.findAllByPostId(postId);
    }
}
