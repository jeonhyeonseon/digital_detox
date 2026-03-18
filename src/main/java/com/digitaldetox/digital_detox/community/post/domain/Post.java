package com.digitaldetox.digital_detox.community.post.domain;

import com.digitaldetox.digital_detox.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tbl_post")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

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
