package com.example.CRUD_Post.controller;

import com.example.CRUD_Post.exception.SuccessMessage;
import com.example.CRUD_Post.post.Post;
import com.example.CRUD_Post.repository.PostRepository;
import com.example.CRUD_Post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(method = RequestMethod.GET, params = "title")
    public ResponseEntity<List<Post>> getPostsByTitleContainingIgnoreCase(@RequestParam(required = false) String title) {
        return postService.getPostsByTitleContainingIgnoreCase(title);
    }

    @GetMapping(path = "{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") long id) {
        return postService.getPostById(id);
    }

    @GetMapping(path = "published")
    public ResponseEntity<List<Post>> getPostsByPublished() {
        return postService.getPostsByPublished();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping(path = "{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") long id,
                                           @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping(path = "{postId}")
    public ResponseEntity<SuccessMessage> deletePost(@PathVariable("postId") long id) {
        return postService.deletePost(id);
    }
}
