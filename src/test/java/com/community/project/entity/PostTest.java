package com.community.project.entity;

import com.community.project.service.MemberService;
import com.community.project.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.community.project.entity.AccountType.Realtor;

@Transactional
@SpringBootTest
class PostTest {

    @Autowired
    PostService postService;

    @Autowired
    MemberService memberService;

    @Test
    public void BaseTimeTest() {
        Post post = new Post();
        Post createdPost = postService.savePost(post);

        System.out.println("createdPost.getCreated() = " + createdPost.getCreated());
        System.out.println("createdPost.getModified() = " + createdPost.getModified());

        Assertions.assertThat(post).isEqualTo(createdPost);

        memberService.findAll();
    }

    @Test
    public void updateViewCount() {
        Member member = Member.builder().accountType(Realtor)
                .nickname("kihwan").accountId("501").quit(false).build();
        Post post = Post.builder().content("무제한 컨텐츠입니다 하하하 http:101")
                .title("무제한 컨텐츠").member(member).build();

        memberService.saveMember(member);
        postService.savePost(post);

        postService.updateViewCount(post.getId());

        Post updatedPost = postService.getPost(post.getId());

        System.out.println("updatedPost.getViewCount() = " + updatedPost.getViewCount());
    }

    @Test
    public void deletePost() {
        Member member = Member.builder().accountType(Realtor)
                .nickname("kihwan").accountId("501").build();
        Post post = Post.builder().content("무제한 컨텐츠입니다 하하하 http:101")
                .title("무제한 컨텐츠").isDeleted(false).member(member).build();

        System.out.println("post.getIsDeleted() = " + post.getIsDeleted());

        memberService.saveMember(member);
        Post newPost = postService.savePost(post);

        postService.deletePost(newPost.getId());

        Post deleted = postService.getPost(post.getId());

        Assertions.assertThat(true).isEqualTo(deleted.getIsDeleted());
    }
}