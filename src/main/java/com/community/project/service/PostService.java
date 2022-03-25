package com.community.project.service;

import com.community.project.entity.Post;
import com.community.project.entity.PostLike;
import com.community.project.repository.PostLikeRepo;
import com.community.project.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
@Transactional @Slf4j
public class PostService {

    private final PostRepo postRepo;
    private final PostLikeRepo postLikeRepo;

    public Post savePost(Post post) {
        return postRepo.save(post);
    }

    public int updateViewCount(Long postId) {
        return postRepo.updateViewCount(postId);
    }

    public Post getPost(Long postId) {
        return postRepo.getById(postId);
    }

    public List<Post> getPosts() {
        return postRepo.findAll();
    }

    public Boolean deletePost(Long postId) {
        Post post = postRepo.getById(postId);
        post.setIsDeleted(true);
        return post.getIsDeleted();
    }
}
