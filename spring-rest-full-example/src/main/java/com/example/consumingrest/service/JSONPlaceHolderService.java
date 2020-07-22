package com.example.consumingrest.service;

import com.example.consumingrest.model.Post;

import java.util.List;

public interface JSONPlaceHolderService {

    List<Post> getPosts();

    Post getPostById(Long id);
}