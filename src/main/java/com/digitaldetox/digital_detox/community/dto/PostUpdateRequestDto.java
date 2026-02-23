package com.digitaldetox.digital_detox.community.dto;

import com.digitaldetox.digital_detox.community.domain.Post;
import com.digitaldetox.digital_detox.community.domain.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequestDto {

    private Long postId;
    private Long memberId;
    private PostCategory postCategory;
    private String title;
    private String content;

    public static PostUpdateRequestDto fromPost(Post post) {
        return new PostUpdateRequestDto(
                post.getPostId(),
                post.getMemberId(),
                post.getPostCategory(),
                post.getTitle(),
                post.getContent()
        );
    }
}
