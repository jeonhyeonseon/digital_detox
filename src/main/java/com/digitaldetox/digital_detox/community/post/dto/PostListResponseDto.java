package com.digitaldetox.digital_detox.community.post.dto;

import com.digitaldetox.digital_detox.community.post.domain.Post;
import com.digitaldetox.digital_detox.community.post.domain.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostListResponseDto {

    private Long postId;
    private PostCategory postCategory;

    private String title;
    private String content;

    private int commentCount;
    private int likeCount;
    private int viewCount;

    public static PostListResponseDto fromPost(Post post) {
        return PostListResponseDto.builder()
                .postId(post.getPostId())
                .postCategory(post.getPostCategory())
                .title(post.getTitle())
                .content(post.getContent())
                .commentCount(post.getCommentCount())
                .likeCount(post.getLikeCount())
                .viewCount(post.getViewCount())
                .build();
    }
}
