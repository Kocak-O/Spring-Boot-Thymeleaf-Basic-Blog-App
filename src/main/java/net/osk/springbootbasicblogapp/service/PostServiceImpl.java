package net.osk.springbootbasicblogapp.service;

import net.osk.springbootbasicblogapp.dto.PostDto;
import net.osk.springbootbasicblogapp.entity.Post;
import net.osk.springbootbasicblogapp.entity.User;
import net.osk.springbootbasicblogapp.mapper.PostMapper;
import net.osk.springbootbasicblogapp.repository.PostRepository;
import net.osk.springbootbasicblogapp.repository.UserRepository;
import net.osk.springbootbasicblogapp.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository repository){
        this.postRepository = postRepository;
        this.userRepository = repository;
    }


    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getEmail();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findByPostUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        return postRepository.searchPosts(query).stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getEmail();
        User user = userRepository.findByEmail(email);
        Long userId = user.getId();
        List<Post> posts = postRepository.findPostByUser(userId);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }
}
