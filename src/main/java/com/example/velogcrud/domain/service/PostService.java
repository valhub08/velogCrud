package com.example.velogcrud.domain.service;

import com.example.velogcrud.application.dao.PostRepository;
import com.example.velogcrud.domain.entity.Post;
import com.example.velogcrud.application.dto.request.PostRequest;
import com.example.velogcrud.application.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostResponse createPost(PostRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .authorName(request.getAuthorName())
                .build();

        return PostResponse.from(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {
        return PostResponse.from(findPostById(postId));
    }

    @Transactional
    public PostResponse updatePost(Long postId, PostRequest request) {
        Post post = findPostById(postId);
        post.update(request.getTitle(), request.getContent());
        return PostResponse.from(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = findPostById(postId);
        postRepository.delete(post);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    }
}
