package com.community.project.service;

import com.community.project.entity.Member;
import com.community.project.entity.Post;
import com.community.project.entity.PostLike;
import com.community.project.repository.PostLikeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class PostLikeServiceTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    PostService postService;

    @Autowired
    MemberService memberService;

    @Autowired
    PostLikeService postLikeService;

    @Autowired
    PostLikeRepo postLikeRepo;

    @Test
    @BeforeEach
    public void before() {
        Member member1 = new Member("KK", "공인중개사", false);
        memberService.saveMember(member1);
        Post post1 = Post.builder().content("무제한 컨텐츠입니다 하하하 http:101")
                .title("무제한 컨텐츠").viewCount(0L)
                .isDeleted(false).build();
        postService.savePost(member1.getId(), post1);

        System.out.println("post1.getId() = " + post1.getId());
        System.out.println("member1.getId() = " + member1.getId());
    }

    @Test
    public void testCounterWithConcurrency() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                postLikeService.savePostLike(13L, 14L);
            });
        }
    }
}