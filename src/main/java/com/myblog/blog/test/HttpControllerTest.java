package com.myblog.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {
    @GetMapping
    public String getTest(){
        return "get";
    }
    @PostMapping
    public String postTest(){
        return "post";
    }
    @PutMapping
    public String putTest(){
        return "put";
    }
    @DeleteMapping
    public String deleteTest(){
        return "delete";
    }
}
