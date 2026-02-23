package com.digitaldetox.digital_detox.community.dto;

import com.digitaldetox.digital_detox.community.domain.Post;
import com.digitaldetox.digital_detox.community.domain.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDetailDto {

    private Long postId;
    private Long memberId;
    private PostCategory postCategory;
    private String title;
    private String content;

    public static PostResponseDetailDto fromPost(Post post) {
        return new PostResponseDetailDto(
                post.getPostId(),
                post.getMemberId(),
                post.getPostCategory(),
                post.getTitle(),
                post.getContent()
        );
    }
}
