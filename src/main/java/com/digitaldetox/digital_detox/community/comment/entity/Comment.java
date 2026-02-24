package com.digitaldetox.digital_detox.community.comment.entity;

import com.digitaldetox.digital_detox.community.comment.dto.CommentCreatedRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_comment")
@Setter(AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long postId;
    private Long memberId;

    @Column(length = 500, nullable = false)
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(Long postId, Long memberId, CommentCreatedRequestDto createdRequestDto) {
        this.postId = postId;
        this.memberId = memberId;
        this.content = createdRequestDto.getContent();
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}
