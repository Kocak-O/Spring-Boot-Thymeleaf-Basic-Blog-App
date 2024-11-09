package net.osk.springbootbasicblogapp.controller;

import jakarta.validation.Valid;
import net.osk.springbootbasicblogapp.dto.CommentDto;
import net.osk.springbootbasicblogapp.dto.PostDto;
import net.osk.springbootbasicblogapp.service.CommentService;
import net.osk.springbootbasicblogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;

    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl, @Valid @ModelAttribute("comment") CommentDto commentDto, BindingResult result, Model model){
        PostDto postDto = postService.findByPostUrl(postUrl);

        if (result.hasErrors()){
            model.addAttribute("comment", commentDto);
            model.addAttribute("postDto", postDto);
            return "blog/blog_post";
        }
        commentService.createComment(postUrl, commentDto);
        return "redirect:/post/" + postUrl;
    }



}
