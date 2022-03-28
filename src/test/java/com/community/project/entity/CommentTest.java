package com.community.project.entity;

import com.community.project.repository.CommentRepo;
import com.community.project.service.CommentService;
import com.community.project.service.MemberService;
import com.community.project.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class CommentTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    PostService postService;

    @Autowired
    MemberService memberService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepo commentRepo;

    @Test
    public void test() {
        Member member = new Member("aaa", "공인중개사", false);

        Post post = Post.builder().content("무제한 컨텐츠입니다 하하하 http:101")
                .title("무제한 컨텐츠").member(member).build();

        memberService.saveMember(member);
        postService.savePost(member.getId(), post);

        Comment comment = new Comment("aa", post, member);

    }
}