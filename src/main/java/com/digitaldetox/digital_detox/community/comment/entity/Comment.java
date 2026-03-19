package com.digitaldetox.digital_detox.community.comment.entity;

import com.digitaldetox.digital_detox.community.comment.dto.CommentCreatedRequestDto;
import com.digitaldetox.digital_detox.community.post.domain.Post;
import com.digitaldetox.digital_detox.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_comment")
@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @Column(length = 500, nullable = false)
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(Post post, Member member, CommentCreatedRequestDto createdRequestDto) {
        this.post = post;
        this.member = member;
        this.content = createdRequestDto.getContent();
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}
