package com.digitaldetox.digital_detox.community.comment.service;

import com.digitaldetox.digital_detox.community.comment.dto.CommentCreatedRequestDto;
import com.digitaldetox.digital_detox.community.comment.dto.CommentListResponseDto;
import com.digitaldetox.digital_detox.community.comment.dto.CommentUpdateRequestDto;
import com.digitaldetox.digital_detox.community.comment.entity.Comment;
import com.digitaldetox.digital_detox.community.comment.repository.CommentRepository;
import com.digitaldetox.digital_detox.member.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentListResponseDto> listComment() {

        return commentRepository.findAll()
                                            .stream().map(CommentListResponseDto::fromComment)
                                            .toList();

    }

    public Long createComment(Long postId, Member member, CommentCreatedRequestDto createdRequestDto) {

        Comment comment = new Comment(postId, member, createdRequestDto);

        Comment saved = commentRepository.save(comment);

        return saved.getCommentId();
    }

    public void updatedComment(Long commentId, CommentUpdateRequestDto updateRequestDto) {

        Comment comment = commentRepository.findById(commentId)
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        comment.updateContent(updateRequestDto.getContent());
    }

    public void deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        commentRepository.delete(comment);
    }
}
