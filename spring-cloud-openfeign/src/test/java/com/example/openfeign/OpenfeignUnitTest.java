package com.example.openfeign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OpenfeignUnitTest {

    @Autowired
    private PostClient client;

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

    @Test
    public void shouldLoadAllPosts() {
        final List<Post> posts = client.getPosts();
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
    }

    @Test
    public void whenGetPostWithId_thenPostExist() {
        Post post = client.getPostById(1L);
        assertNotNull(post);
    }

}