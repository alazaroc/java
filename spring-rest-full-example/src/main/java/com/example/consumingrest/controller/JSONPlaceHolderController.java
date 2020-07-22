package com.example.consumingrest.controller;


import com.example.consumingrest.model.Post;
import com.example.consumingrest.service.JSONPlaceHolderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class JSONPlaceHolderController {

    @Autowired
    private JSONPlaceHolderService service;

    @Operation(summary = "List of all posts")
    @GetMapping(value="/posts")
    public List<Post> getPosts() {
        return service.getPosts();
    }

    @Operation(summary = "Get a post by id")
    @GetMapping(value="/posts/{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return service.getPostById(id);
    }
}
