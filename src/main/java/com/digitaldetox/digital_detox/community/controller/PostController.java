package com.digitaldetox.digital_detox.community.controller;

import com.digitaldetox.digital_detox.community.dto.PostRequestRegisterDto;
import com.digitaldetox.digital_detox.community.dto.PostResponseDetailDto;
import com.digitaldetox.digital_detox.community.dto.PostUpdateRequestDto;
import com.digitaldetox.digital_detox.community.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String showPostFrm() {

        return "community/register";
    }

    @PostMapping
    public String registerPost(@ModelAttribute PostRequestRegisterDto postRequestRegisterDto) {

        postService.registerPost(postRequestRegisterDto);

        return "redirect:/register";
    }

    @GetMapping("/{postId}")
    public String detailPost(@PathVariable Long postId,
                             Model model) {

        PostResponseDetailDto detailPost = postService.getPostDetail(postId);

        model.addAttribute("post", detailPost);

        return "community/detail";
    }

    @GetMapping("/{postId}/edit")
    public String showPostUpdateFrm(@PathVariable Long postId,
                                  Model model) {

        PostUpdateRequestDto postUpdateRequestDto = postService.getPostForUpdate(postId);

        model.addAttribute("updatePost", postUpdateRequestDto);

        return "community/edit";
    }

    @PostMapping("/{postId}/edit")
    public String updatePost(@PathVariable Long postId,
                             @ModelAttribute PostUpdateRequestDto postUpdateRequestDto) {

        postService.updatePost(postId, postUpdateRequestDto);

        return "redirect:/community" + postId;
    }
}
