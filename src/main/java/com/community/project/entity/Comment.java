package com.community.project.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(name = "JoinMemberAndPost", attributeNodes = {@NamedAttributeNode("member"), @NamedAttributeNode("post")})
public class Comment extends BaseTime {
    @Id @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Comment(String comment, Post post, Member member) {
        this.comment = comment;
        this.post = post;
        this.member = member;
    }
}
