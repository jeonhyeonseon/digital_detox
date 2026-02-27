package com.digitaldetox.digital_detox.community.dto;

import com.digitaldetox.digital_detox.community.post.dto.PostListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityPostListResponseDto {

    private List<PostListResponseDto> popularPosts;
    private List<PostListResponseDto> latestPosts;

}
