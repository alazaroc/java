package com.example.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class PostClient {
    WebClient webClient;

    public PostClient(@Value("${client.url}") final String baseUrl) {
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(10))
                                        .addHandlerLast(new WriteTimeoutHandler(10))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(connector)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * Get all resources
     **/
    Flux<Post> getPosts() {
        Flux<Post> response = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class);
        return response;
    }

    /**
     * Get id resource
     **/
    Mono<Post> getPostById(Long id) {
        Mono<Post> response = webClient.get()
                .uri("/posts/" + id)
                .retrieve()
                /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())*/
                .bodyToMono(Post.class);
        return response;
    }

    /**
     * Create new resource
     **/
    public Mono<Post> createPost(Post post) {
        Mono<Post> response = webClient.post()
                .uri("/posts/" + post.getId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(post), Post.class)
                .retrieve()
                .bodyToMono(Post.class);
        return response;
    }

    /**
     * Update resource
     **/
    public Mono<Post> updatePost(Post post) {
        Mono<Post> response = webClient.put()
                .uri("/posts/" + post.getId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(post), Post.class)
                .retrieve()
                .bodyToMono(Post.class);
        return response;
    }

    /**
     * Delete resource
     **/
    public Mono<Void> deletePost(Post post) {
        Mono<Void> response = webClient.delete()
                .uri("/post" + post.getId())
                .retrieve()
                .bodyToMono(Void.class);
        return response;
    }

}
