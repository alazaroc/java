package com.example.swagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Api(tags = "Item controller", description = "item operations")
public class Controller {

    // Example with GetMapping
    @ApiOperation(value = "List of all items", response = String.class, responseContainer = "List")
    @GetMapping(value="/item")
    public List<String> getAllItems() {
        ArrayList<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        return list;
    }

    // Example with RequestMapping and get
    @ApiOperation(value = "Get item", response = Long.class)
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public Long getItem(@PathVariable("id") Long id) {
        return id;
    }

    // Example with RequestMapping and post
    @ApiOperation(value = "Post item", response = String.class)
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity<String> postItem(@RequestBody String value) {
        // TODO: create item
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
