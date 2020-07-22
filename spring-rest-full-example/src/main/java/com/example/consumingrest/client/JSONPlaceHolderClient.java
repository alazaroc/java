package com.example.consumingrest.client;

import com.example.consumingrest.config.ClientConfiguration;
import com.example.consumingrest.hystrix.JSONPlaceHolderFallback;
import com.example.consumingrest.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(
        value = "jplaceholder",
        url = "${client.url}",
        configuration = ClientConfiguration.class,
        fallback = JSONPlaceHolderFallback.class)
// We tell Feign to use OkHttpClient instead of the default one in order to support HTTP/2.
public interface JSONPlaceHolderClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<Post> getPosts();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}", produces = "application/json")
    Post getPostById(@PathVariable("postId") Long postId);
}
