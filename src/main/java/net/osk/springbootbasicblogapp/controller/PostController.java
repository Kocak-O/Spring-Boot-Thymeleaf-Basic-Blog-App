package net.osk.springbootbasicblogapp.controller;

import jakarta.validation.Valid;
import net.osk.springbootbasicblogapp.dto.PostDto;
import net.osk.springbootbasicblogapp.entity.Comment;
import net.osk.springbootbasicblogapp.service.CommentService;
import net.osk.springbootbasicblogapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostService postService;
    private CommentService commentService;


    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/admin/posts")
    public String posts(Model model){
        model.addAttribute("posts", postService.findPostsByUser());
        return "/admin/posts";
    }

    @GetMapping("/newpost")
    public String newPostForm(Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "/admin/create_post";
    }

    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("post", postDto);
            return "/admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    private static String getUrl(String postTitle){
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]]", "-");
        return url;
    }

    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("post", postDto);
            return "admin/edit_post";
        }

        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping( "/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }

    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto postDto = postService.findByPostUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }

    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query, Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("post", posts);
        return "admin/posts";
    }

    @GetMapping("/admin/posts/comments")
    public String viewComments(Model model){
        List<Comment> comments = commentService.findAllComments();
        model.addAttribute("comments", comments);
        return "admin/comments";
    }

    @GetMapping("/admin/posts/comments/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";
    }

}
