package com.digitaldetox.digital_detox.community.comment.repository;

import com.digitaldetox.digital_detox.community.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
