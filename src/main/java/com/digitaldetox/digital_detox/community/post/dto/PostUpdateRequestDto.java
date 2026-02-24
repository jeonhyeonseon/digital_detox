package com.digitaldetox.digital_detox.community.post.dto;

import com.digitaldetox.digital_detox.community.post.domain.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequestDto {

    private Long postId;
    private Long memberId;
    private PostCategory postCategory;
    private String title;
    private String content;

}
