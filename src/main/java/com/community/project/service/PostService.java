package com.community.project.service;

import com.community.project.entity.Member;
import com.community.project.entity.Post;
import com.community.project.error.CreateException;
import com.community.project.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.community.project.error.ErrorCode.UNAUTHORIZED_USER;

@Service @RequiredArgsConstructor
@Transactional @Slf4j
public class PostService {

    private final PostRepo postRepo;
    private final MemberService memberService;

    public Post savePost(Long id, Post post) {
        Member member = memberService.getMember(id);
        post.setMember(member);
        return postRepo.save(post);
    }

    public int updateViewCount(Long id) {
        return postRepo.updateViewCount(id);
    }

    public Post getPost(Long id) {
        return postRepo.getById(id);
    }

    public List<Post> getPosts() {
        return postRepo.findAll();
    }

    public Boolean deletePost(Long memberId, Long postId) {
        Member member = memberService.getMember(memberId);
        Post post = postRepo.getById(postId);

        if (post.getMember().getId() != member.getId()) throw new CreateException(UNAUTHORIZED_USER);

        post.setIsDeleted(true);
        return post.getIsDeleted();
    }
}
