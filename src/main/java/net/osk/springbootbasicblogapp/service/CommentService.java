package net.osk.springbootbasicblogapp.service;

import net.osk.springbootbasicblogapp.dto.CommentDto;
import net.osk.springbootbasicblogapp.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CommentService {

    void createComment(String postUrl, CommentDto commentDto);

    List<Comment> findAllComments();

    void deleteComment(Long commentId);
}
