package com.example.CRUD_Post.service;

import com.example.CRUD_Post.exception.ResouceNotFoundException;
import com.example.CRUD_Post.exception.SuccessMessage;
import com.example.CRUD_Post.post.Post;
import com.example.CRUD_Post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    public ResponseEntity<List<Post>> getPostsByTitleContainingIgnoreCase(String title) {
        List<Post> posts =
                postRepository.findPostsByTitleContainingIgnoreCase(title);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    public ResponseEntity<Post> getPostById(Long id) {
        Post post =
                postRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Not found Tutorial with id = " + id));
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    public ResponseEntity<List<Post>> getPostsByPublished() {
        List<Post> posts = postRepository.findPostsByPublished(true);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    public ResponseEntity<Post> createPost(Post post) {
        Post _post = postRepository.save(post);
        return new ResponseEntity<>(_post, HttpStatus.CREATED);
    }

    public ResponseEntity<Post> updatePost(long id, Post post) {
        Post _post =
                postRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Not found Tutorial with id = " + id));
        _post.setTitle(post.getTitle());
        _post.setDescription(post.getDescription());
        _post.setImage(post.getImage());
        _post.setPublished(post.isPublished());
        postRepository.save(_post);
        return new ResponseEntity<>(_post, HttpStatus.OK);
    }

    public ResponseEntity<SuccessMessage> deletePost(long id) {
        postRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Not found Tutorial with id = " + id + ", Can't delete"));
        postRepository.deleteById(id);
        SuccessMessage successMessage =
                new SuccessMessage("Delete Success " + "Tutorial with id = " + id);
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}
