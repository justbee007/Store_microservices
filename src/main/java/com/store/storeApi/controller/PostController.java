package com.store.storeApi.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.store.storeApi.user.Post;
import com.store.storeApi.user.PostRepository;
import com.store.storeApi.user.User;
import com.store.storeApi.user.UserRepository;

@RestController
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Create a new post for a specific user
    @PostMapping("posts/users/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable int userId, @RequestBody Post post) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();
        
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }

    // Get all posts for a specific user
    @GetMapping("posts/users/{userId}")
    public ResponseEntity<List<Post>> getAllPostsByUser(@PathVariable int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<Post> posts = postRepository.findByUserId(userId);
        return ResponseEntity.ok(posts);
    }


//    @GetMapping("/{postId}")
//    public ResponseEntity<Post> getPostById(@PathVariable int userId, @PathVariable int postId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (!optionalUser.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Optional<Post> optionalPost = postRepository.findById(postId);
//        if (!optionalPost.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Post post = optionalPost.get();
//        if (post.getUser().getId() != userId) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        return ResponseEntity.ok(post);
//    }

    // Update a post for a specific user
//    @PutMapping("/{postId}")
//    public ResponseEntity<Post> updatePost(@PathVariable int userId, @PathVariable int postId, @RequestBody Post postDetails) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (!optionalUser.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Optional<Post> optionalPost = postRepository.findById(postId);
//        if (!optionalPost.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Post post = optionalPost.get();
//        if (post.getUser().getId() != userId) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        post.setDescription(postDetails.getDescription());
//        Post updatedPost = postRepository.save(post);
//        return ResponseEntity.ok(updatedPost);
//    }

    // Delete a post for a specific user
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable int userId, @PathVariable int postId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (!optionalPost.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Post post = optionalPost.get();
        if (post.getUser().getId() != userId) {
            return ResponseEntity.badRequest().build();
        }

        postRepository.delete(post);
        return ResponseEntity.noContent().build();
    }
}
