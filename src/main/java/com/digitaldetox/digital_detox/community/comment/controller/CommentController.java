package com.digitaldetox.digital_detox.community.comment.controller;

import com.digitaldetox.digital_detox.community.comment.dto.CommentCreatedRequestDto;
import com.digitaldetox.digital_detox.community.comment.dto.CommentListResponseDto;
import com.digitaldetox.digital_detox.community.comment.dto.CommentUpdateRequestDto;
import com.digitaldetox.digital_detox.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/community/{postId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentListResponseDto> listComment() {
        return commentService.listComment();
    }

    @PostMapping
    public Map<String, Long> createComment(@PathVariable Long postId,
                                           @RequestBody CommentCreatedRequestDto createdRequestDto) {

        Long memberId = 1L; // TODO: 사용자

        Long commentId = commentService.createComment(postId, memberId, createdRequestDto);

        return Map.of("commentId", commentId);
    }

    @PutMapping("/{commentId}")
    public Map<String, Boolean> updateComment(@PathVariable Long commentId,
                                              @RequestBody CommentUpdateRequestDto updateRequestDto) {

        commentService.updatedComment(commentId, updateRequestDto);

        return Map.of("updated", true);
    }

    @DeleteMapping("/{commentId}")
    public Map<String, Boolean> deleteComment(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);

        return Map.of("deleted", true);
    }
}
