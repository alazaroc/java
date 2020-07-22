package com.example.openfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PostController {

    @Autowired
    private PostClient client;

    @GetMapping(value="/posts")
    public List<Post> getPosts() {
        return client.getPosts();
    }

    @GetMapping(value="/posts/{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return client.getPostById(id);
    }
}
