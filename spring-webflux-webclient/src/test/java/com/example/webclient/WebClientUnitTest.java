package com.example.webclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WebClientUnitTest {
    private PostClient client;

    @BeforeEach
    void setUp() {
        String baseUrl = "https://jsonplaceholder.typicode.com";
        client = new PostClient(baseUrl);
    }

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

    @Test
    public void shouldLoadAllPosts() {
        final Flux<Post> posts = client.getPosts();
        assertNotNull(posts);
    }

    @Test
    public void whenGetPostWithId_thenPostExist() {
        Mono<Post> post = client.getPostById(1L);
        assertNotNull(post);
    }

}