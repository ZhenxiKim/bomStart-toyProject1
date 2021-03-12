package com.bom.start.service;

import com.bom.start.domain.Posts;
import com.bom.start.domain.PostsRespository;
import com.bom.start.web.dto.PostSaveRequestDto;
import com.bom.start.web.dto.PostsListResponseDto;
import com.bom.start.web.dto.PostsResponseDto;
import com.bom.start.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRespository postsRespository;

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRespository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public void delete(Long id) {
        Posts posts = postsRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        postsRespository.delete(posts);
    }

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return postsRespository.save(requestDto.toEntity()).getId();
    }


    public PostsResponseDto findById(Long id){
        Posts entity = postsRespository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }
}
