package com.digitaldetox.digital_detox.community.comment.service;

import com.digitaldetox.digital_detox.community.comment.dto.CommentCreatedRequestDto;
import com.digitaldetox.digital_detox.community.comment.entity.Comment;
import com.digitaldetox.digital_detox.community.comment.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Long createComment(Long postId, Long memberId, CommentCreatedRequestDto createdRequestDto) {

        Comment comment = new Comment(postId, memberId, createdRequestDto);

        Comment saved = commentRepository.save(comment);

        return saved.getCommentId();
    }
}
