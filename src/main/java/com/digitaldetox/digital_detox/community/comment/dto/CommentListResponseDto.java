package com.digitaldetox.digital_detox.community.comment.dto;

import com.digitaldetox.digital_detox.community.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentListResponseDto {

    private Long postId;
    private Long memberId;
    private String nickname;
    private String content;

    private LocalDateTime createdAt;

    public static CommentListResponseDto fromComment(Comment comment) {
        return CommentListResponseDto.builder()
                .postId(comment.getPost().getPostId())
                .memberId(comment.getMember().getMemberId())
                .nickname(comment.getMember().getNickname())
                .content(comment.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
