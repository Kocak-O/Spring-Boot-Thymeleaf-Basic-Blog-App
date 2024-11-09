package net.osk.springbootbasicblogapp.mapper;


import net.osk.springbootbasicblogapp.dto.PostDto;
import net.osk.springbootbasicblogapp.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .url(post.getUrl())
                .content(post.getContent())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .title(post.getTitle())
                .shortDescription(post.getShortDescription())
                .comments(post.getComments().stream().map((comment -> CommentMapper.mapToCommentDto(comment)))
                        .collect(Collectors.toSet()))
                .build();
    }

    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .title(postDto.getTitle())
                .shortDescription(postDto.getShortDescription())
                .build();
    }



}
