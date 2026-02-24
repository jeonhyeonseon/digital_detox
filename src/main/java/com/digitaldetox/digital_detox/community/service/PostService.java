package com.digitaldetox.digital_detox.community.service;

import com.digitaldetox.digital_detox.community.domain.Post;
import com.digitaldetox.digital_detox.community.dto.PostRegisterRequestDto;
import com.digitaldetox.digital_detox.community.dto.PostDetailResponseDto;
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

    public Long registerPost(PostRegisterRequestDto postRegisterRequestDto) {

        Post post = postRegisterRequestDto.toPost();
        Post saved = postRepository.save(post);
        return saved.getPostId();
    }

    public PostDetailResponseDto detailPost(Long postId) {

        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return PostDetailResponseDto.fromPost(post);
    }

    public void updatePost(Long postId, PostUpdateRequestDto updateRequestDto) {

        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        post.updatePost(updateRequestDto.getPostCategory(), updateRequestDto.getTitle(), updateRequestDto.getContent());
    }

    public void deletePost(Long postId) {

        Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        postRepository.delete(post);
    }
}
