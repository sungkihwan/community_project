package com.community.project.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@IdClass(PostLikeId.class)
public class PostLike extends BaseTime {

    @Id
    @Column(name = "member_id")
    private Long memberId;

    @Id
    @Column(name = "post_id")
    private Long postId;
}
