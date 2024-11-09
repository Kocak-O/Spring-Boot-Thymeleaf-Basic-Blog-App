package net.osk.springbootbasicblogapp.service;

import net.osk.springbootbasicblogapp.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);

    PostDto findByPostUrl(String postUrl);

    List<PostDto> searchPosts(String query);

    List<PostDto> findPostsByUser();
}
