package com.example.resttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PostClient {

    private static final Logger log = LoggerFactory.getLogger(PostClient.class);

    @Value("${client.url}")
    public String clientUrl;
    public RestTemplate restTemplate;

    public PostClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<Post> getPosts() {
        List<Post> postList = restTemplate.getForObject(
                clientUrl + "/posts", List.class);
        log.info(postList.toString());
        return postList;
    }

    public Post getPostById(Long postId) {
        Post post = restTemplate.getForObject(
                clientUrl + "/posts/" + postId, Post.class);
        log.info(post.toString());
        return post;

    }
}
