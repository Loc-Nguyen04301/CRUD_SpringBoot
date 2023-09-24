package com.example.CRUD_Post.repository;

import com.example.CRUD_Post.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByPublished(boolean published);

    List<Post> findPostsByTitleContainingIgnoreCase(String title);
}
