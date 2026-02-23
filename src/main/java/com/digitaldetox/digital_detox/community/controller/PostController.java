package com.digitaldetox.digital_detox.community.controller;

import com.digitaldetox.digital_detox.community.domain.Post;
import com.digitaldetox.digital_detox.community.dto.PostRequestDto;
import com.digitaldetox.digital_detox.community.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String registerPost(@ModelAttribute PostRequestDto postRequestDto) {

        postService.registerPost(postRequestDto);

        return "redirect:/register";
    }
}
