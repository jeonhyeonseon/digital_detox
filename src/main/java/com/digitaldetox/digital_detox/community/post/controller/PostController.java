package com.digitaldetox.digital_detox.community.post.controller;

import com.digitaldetox.digital_detox.community.post.dto.PostListResponseDto;
import com.digitaldetox.digital_detox.community.post.dto.PostRegisterRequestDto;
import com.digitaldetox.digital_detox.community.post.dto.PostDetailResponseDto;
import com.digitaldetox.digital_detox.community.post.dto.PostUpdateRequestDto;
import com.digitaldetox.digital_detox.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostListResponseDto> listPost() {
        return postService.listPost();
    }

    @PostMapping
    public Map<String, Long> registerPost(@RequestBody PostRegisterRequestDto postRegisterRequestDto) {

        Long postId = postService.registerPost(postRegisterRequestDto);

        return Map.of("postId", postId);
    }

    @GetMapping("/{postId}")
    public PostDetailResponseDto detailPost(@PathVariable Long postId) {

        return postService.detailPost(postId);
    }

    @PutMapping("/{postId}")
    public Map<String, Boolean> updatePost(@PathVariable Long postId,
                                           @RequestBody PostUpdateRequestDto postUpdateRequestDto) {

        postService.updatePost(postId, postUpdateRequestDto);

        return Map.of("updated", true);
    }

    @DeleteMapping("/{postId}")
    public Map<String, Boolean> deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);

        return Map.of("deleted", true);
    }

}
