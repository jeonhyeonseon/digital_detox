package com.digitaldetox.digital_detox.community.post.dto;

import com.digitaldetox.digital_detox.community.post.domain.Post;
import com.digitaldetox.digital_detox.community.post.domain.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRegisterRequestDto {

    private Long memberId;

    private PostCategory postCategory;

    private String title;

    private String content;

    private LocalDate createdAt;

    public Post toPost() {
        return Post.builder()
                .member(this.toPost().getMember())
                .postCategory(this.postCategory)
                .title(this.title)
                .content(this.content)
                .createdAt(LocalDate.now())
                .build();
    }
}
