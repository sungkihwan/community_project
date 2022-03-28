package com.community.project;

import com.community.project.entity.Member;
import com.community.project.entity.Post;
import com.community.project.entity.PostLike;
import com.community.project.repository.PostLikeRepo;
import com.community.project.service.MemberService;
import com.community.project.service.PostService;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

	@Bean
	@Transactional
	CommandLineRunner run(MemberService memberService, PostService postService, PostLikeRepo postLikeRepo) {
		return args -> {
			Member member1 = new Member("KK", "공인중개사", false);
			Member member2 = new Member("uu", "임대인", false);
			Member member3 = new Member("ll", "임차인", false);
			Member member4 = new Member("ii", "임대인", false);
			Member member5 = new Member("dd", "공인중개사", false);

			Member newMember1 = memberService.saveMember(member1);
			Member newMember2 = memberService.saveMember(member2);
			Member newMember3 = memberService.saveMember(member3);
			Member newMember4 = memberService.saveMember(member4);
			Member newMember5 = memberService.saveMember(member5);

			Post post1 = Post.builder().content("무제한 컨텐츠입니다 하하하 http:101")
					.title("무제한 컨텐츠").viewCount(0L)
					.isDeleted(false).build();

			Post post2 = Post.builder().content("개미는 오늘도 뚠뚠 일을한다 뚠뚠")
					.title("냠냠이").viewCount(0L)
					.isDeleted(false).build();

			Post post3 = Post.builder().content("하....이게 뭐고")
					.title("숨은").viewCount(0L)
					.isDeleted(false).build();

			Post newPost1 = postService.savePost(member1.getId(), post1);
			Post newPost2 = postService.savePost(member2.getId(), post2);
			Post newPost3 = postService.savePost(member3.getId(), post3);

			PostLike like1 = new PostLike(newMember1, newPost1);
			PostLike like2 = new PostLike(newMember2, newPost1);
			PostLike like3 = new PostLike(newMember3, newPost2);
			PostLike like4 = new PostLike(newMember4, newPost2);

			postLikeRepo.save(like1);
			postLikeRepo.save(like2);
			postLikeRepo.save(like3);
			postLikeRepo.save(like4);
		};
	}
}
