package net.osk.springbootbasicblogapp.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.osk.springbootbasicblogapp.dto.CommentDto;
import net.osk.springbootbasicblogapp.entity.Comment;

public class CommentMapper {


    public static CommentDto mapToCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .createdOn(comment.getCreatedOn())
                .updateOn(comment.getUpdateOn())
                .email(comment.getMail())
                .content(comment.getContent())
                .post(comment.getPost())
                .build();
    }

    public static Comment mapToComment(CommentDto commentDto){
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .post(commentDto.getPost())
                .updateOn(commentDto.getUpdateOn())
                .createdOn(commentDto.getCreatedOn())
                .content(commentDto.getContent())
                .mail(commentDto.getEmail())
                .build();
    }


}
