package com.digitaldetox.digital_detox.community.comment.service;

import com.digitaldetox.digital_detox.community.comment.dto.CommentCreatedRequestDto;
import com.digitaldetox.digital_detox.community.comment.dto.CommentListResponseDto;
import com.digitaldetox.digital_detox.community.comment.dto.CommentUpdateRequestDto;
import com.digitaldetox.digital_detox.community.comment.entity.Comment;
import com.digitaldetox.digital_detox.community.comment.repository.CommentRepository;
import com.digitaldetox.digital_detox.community.post.domain.Post;
import com.digitaldetox.digital_detox.community.post.repository.PostRepository;
import com.digitaldetox.digital_detox.member.entity.Member;
import com.digitaldetox.digital_detox.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public List<CommentListResponseDto> listComment() {

        return commentRepository.findAll()
                                            .stream().map(CommentListResponseDto::fromComment)
                                            .toList();

    }

    public Long createComment(Long postId, Long memberId, CommentCreatedRequestDto createdRequestDto) {

        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("게시글이 존지하지 않습니다."));

        Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Comment comment = new Comment(post, member, createdRequestDto);

        Comment saved = commentRepository.save(comment);

        return saved.getCommentId();
    }

    public void updatedComment(Long postId, Long commentId, Long memberId, CommentUpdateRequestDto updateRequestDto) {

        Comment comment = commentRepository.findById(commentId)
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!comment.getPost().getPostId().equals(postId)) {
            throw new IllegalArgumentException("게시글에 해당하지 않는 댓글입니다.");
        }

        if (!comment.getMember().getMemberId().equals(memberId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        comment.updateContent(updateRequestDto.getContent());
    }

    public void deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        commentRepository.delete(comment);
    }
}
