package com.digitaldetox.digital_detox.community.post.repository;

import com.digitaldetox.digital_detox.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 인기글
    List<Post> findTop5ByOrderByLikeCountDescCreatedAtDesc();

    // 최신글
    List<Post> findTop5ByOrderByCreatedAtDesc();
}
