package com.example.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class PostController {

    @Autowired
    private PostClient client;

    @GetMapping(value = "/posts")
    public Flux<Post> getPosts() {
        return client.getPosts();
    }

    @GetMapping(value = "/posts/{id}")
    public Mono<Post> getPostById(@PathVariable("id") Long id) {
        return client.getPostById(id);
    }

    @PostMapping(value = "/posts/{id}")
    public Mono<Post> createPost(@PathVariable("id") Long id, @RequestBody() Post post) {
        return client.createPost(post);
    }

    @PutMapping(value = "/posts/{id}")
    public Mono<Post> updatePost(@PathVariable("id") Long id, @RequestBody() Post post) {
        return client.updatePost(post);
    }

    @DeleteMapping(value = "/posts/{id}")
    public Mono<Void> deletePost(@PathVariable("id") Long id, @RequestBody() Post post) {
        return client.deletePost(post);
    }
}
