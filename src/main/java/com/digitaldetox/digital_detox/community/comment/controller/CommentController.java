package com.digitaldetox.digital_detox.community.comment.controller;

import com.digitaldetox.digital_detox.community.comment.dto.CommentCreatedRequestDto;
import com.digitaldetox.digital_detox.community.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/community/{postId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Map<String, Long> createComment(@PathVariable Long postId,
                                           @RequestBody CommentCreatedRequestDto createdRequestDto) {

        Long memberId = 1L; // TODO: 사용자

        Long commentId = commentService.createComment(postId, memberId, createdRequestDto);

        return Map.of("commentId", commentId);
    }
}
