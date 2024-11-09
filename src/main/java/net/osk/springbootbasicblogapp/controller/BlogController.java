package net.osk.springbootbasicblogapp.controller;

import net.osk.springbootbasicblogapp.dto.CommentDto;
import net.osk.springbootbasicblogapp.dto.PostDto;
import net.osk.springbootbasicblogapp.entity.Post;
import net.osk.springbootbasicblogapp.mapper.PostMapper;
import net.osk.springbootbasicblogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private PostService postService;



    @GetMapping("/")
    public String viewBlogPosts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("postsResponse", posts);
        return "blog/view_posts";
    }

    @GetMapping("/post/{postUrl}")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto postDto = postService.findByPostUrl(postUrl);
        CommentDto commentDto = new CommentDto();
        model.addAttribute("postDto", postDto);
        model.addAttribute("comment", commentDto);
        return "blog/blog_post";
    }

    @GetMapping("/page/search")
    public String searchPost(@RequestParam("query") String query, Model model){
        List<PostDto> posts = postService.searchPosts(query);
        System.out.println(posts.toString());
        model.addAttribute("postsResponse", posts);
        return "blog/view_posts";
    }


}
