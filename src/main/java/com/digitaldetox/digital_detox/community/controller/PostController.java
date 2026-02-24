package com.digitaldetox.digital_detox.community.controller;

import com.digitaldetox.digital_detox.community.dto.PostRegisterRequestDto;
import com.digitaldetox.digital_detox.community.dto.PostDetailResponseDto;
import com.digitaldetox.digital_detox.community.dto.PostUpdateRequestDto;
import com.digitaldetox.digital_detox.community.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Map<String, Long> registerPost(@RequestBody PostRegisterRequestDto postRegisterRequestDto) {

        Long postId = postService.registerPost(postRegisterRequestDto);

        return Map.of("postId", postId);
    }

    @GetMapping("/{postId}")
    public PostDetailResponseDto detailPost(@PathVariable Long postId) {

        return postService.getPostDetail(postId);
    }

    @PatchMapping("/{postId}")
    public Map<String, Boolean> showPostUpdateFrm(@PathVariable Long postId,
                                                  @ModelAttribute PostUpdateRequestDto postUpdateRequestDto) {

        postService.updatePost(postId, postUpdateRequestDto);

        return Map.of("updated", true);
    }

}
