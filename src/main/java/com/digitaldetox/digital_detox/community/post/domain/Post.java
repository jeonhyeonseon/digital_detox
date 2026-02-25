package com.digitaldetox.digital_detox.community.post.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Long memberId; // 작성자

    @Enumerated(EnumType.STRING)
    private PostCategory postCategory;

    private String title;
    private String content;

    private int commentCount;
    private int likeCount;
    private int viewCount;

    @CreatedDate
    private LocalDate createdAt;

    public void updatePost(PostCategory postCategory, String title, String content) {
        this.postCategory = postCategory;
        this.title = title;
        this.content = content;
    }
}
