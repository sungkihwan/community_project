package com.community.project;

import com.community.project.entity.Member;
import com.community.project.entity.Post;
import com.community.project.entity.PostLike;
import com.community.project.service.MemberService;
import com.community.project.service.PostLikeService;
import com.community.project.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import static com.community.project.entity.AccountType.*;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(MemberService memberService, PostService postService, PostLikeService postLikeService) {
		return args -> {
			Member member1 = Member.builder().accountType(Realtor)
					.nickname("kihwan").accountId("501").quit(false).build();
			Member member2 = Member.builder().accountType(Lessee)
					.nickname("jiwoo").accountId("601").quit(false).build();
			Member member3 = Member.builder().accountType(Lessee)
					.nickname("ryu").accountId("701").quit(false).build();
			Member member4 = Member.builder().accountType(Lessor)
					.nickname("kidong").accountId("801").quit(false).build();

			Member member5 = Member.builder().accountType(Lessor)
					.nickname("nagam").accountId("901").quit(true).build();

			Member newMember1 = memberService.saveMember(member1);
			Member newMember2 = memberService.saveMember(member2);
			Member newMember3 = memberService.saveMember(member3);
			Member newMember4 = memberService.saveMember(member4);
			Member newMember5 = memberService.saveMember(member5);

			Post post1 = Post.builder().content("무제한 컨텐츠입니다 하하하 http:101")
					.title("무제한 컨텐츠")
					.isDeleted(false).member(member1).build();

			Post post2 = Post.builder().content("개미는 오늘도 뚠뚠 일을한다 뚠뚠")
					.title("냠냠이")
					.isDeleted(false).member(member2).build();

			Post post3 = Post.builder().content("하....이게 뭐고")
					.title("숨은")
					.isDeleted(false).member(member3).build();

			Post newPost1 = postService.savePost(post1);
			Post newPost2 = postService.savePost(post2);
			Post newPost3 = postService.savePost(post3);

			PostLike postLike1 = PostLike.builder().postId(newPost1.getId())
					.memberId(newMember1.getId()).build();

			PostLike postLike2 = PostLike.builder().postId(newPost2.getId())
					.memberId(newMember4.getId()).build();

			PostLike postLike3 = PostLike.builder().postId(newPost3.getId())
					.memberId(newMember3.getId()).build();

			PostLike postLike4 = PostLike.builder().postId(newPost1.getId())
					.memberId(newMember2.getId()).build();

			postLikeService.savePostLike(postLike1);
			postLikeService.savePostLike(postLike2);
			postLikeService.savePostLike(postLike3);
			postLikeService.savePostLike(postLike4);
		};
	}
}
