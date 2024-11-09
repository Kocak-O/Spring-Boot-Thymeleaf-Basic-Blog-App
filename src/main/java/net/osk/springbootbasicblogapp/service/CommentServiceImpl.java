package net.osk.springbootbasicblogapp.service;

import net.osk.springbootbasicblogapp.dto.CommentDto;
import net.osk.springbootbasicblogapp.entity.Comment;
import net.osk.springbootbasicblogapp.entity.Post;
import net.osk.springbootbasicblogapp.mapper.CommentMapper;
import net.osk.springbootbasicblogapp.repository.CommentRepository;
import net.osk.springbootbasicblogapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
