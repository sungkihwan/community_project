package com.community.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter @Setter
@NamedEntityGraph(name = "JoinMemberAndComment", attributeNodes = {@NamedAttributeNode("member")})
public class Post extends BaseTime {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Boolean isDeleted;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Long viewCount;

    public Post(Boolean isDeleted, String title, String content, Long viewCount) {
        this.isDeleted = isDeleted;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
