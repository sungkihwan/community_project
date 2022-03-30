package com.community.project.service;

import com.community.project.entity.PostLike;
import com.community.project.entity.Member;
import com.community.project.entity.Post;
import com.community.project.error.CreateException;
import com.community.project.repository.PostLikeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.community.project.error.ErrorCode.DUPLICATE_RESOURCE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostLikeService {

    private final PostLikeRepo postLikeRepo;
    private final PostService postService;
    private final MemberService memberService;

    public void savePostLike(Long memberId, Long postId) {
        Member member = memberService.getMember(memberId);
        Post post = postService.getPost(postId);
        PostLike postLike = new PostLike(member, post);

    }

    public List<PostLike> getPostLikes() {
        return postLikeRepo.findAll();
    }

    public List<PostLike> getLikesByPostId(Long postId) {
        Post post = postService.getPost(postId);
        return postLikeRepo.findPostLikesByPostId(postId);
    }

    public List<PostLike> getLikesByMemberId(Long memberId) {
        return postLikeRepo.findPostLikesByMemberId(memberId);
    }
}
