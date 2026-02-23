package com.digitaldetox.digital_detox.community.service;

import com.digitaldetox.digital_detox.community.domain.Post;
import com.digitaldetox.digital_detox.community.dto.PostRequestRegisterDto;
import com.digitaldetox.digital_detox.community.dto.PostResponseDetailDto;
import com.digitaldetox.digital_detox.community.dto.PostUpdateRequestDto;
import com.digitaldetox.digital_detox.community.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void registerPost(PostRequestRegisterDto postRequestRegisterDto) {

        Post post = postRequestRegisterDto.toPost();

        postRepository.save(post);
    }

    public PostResponseDetailDto getPostDetail(Long postId) {

        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return PostResponseDetailDto.fromPost(post);
    }

    public PostUpdateRequestDto getPostForUpdate(Long postId) {

        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return PostUpdateRequestDto.fromPost(post);
    }

    public void updatePost(Long postId, PostUpdateRequestDto postUpdateRequestDto) {

        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        post.updatePost(postUpdateRequestDto.getPostCategory(), postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent());
    }
}
