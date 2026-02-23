package com.digitaldetox.digital_detox.community.service;

import com.digitaldetox.digital_detox.community.domain.Post;
import com.digitaldetox.digital_detox.community.dto.PostRequestDto;
import com.digitaldetox.digital_detox.community.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void registerPost(PostRequestDto postRequestDto) {

        Post post = postRequestDto.toPost();

        postRepository.save(post);
    }
}
