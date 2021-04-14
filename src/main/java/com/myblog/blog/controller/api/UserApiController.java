package com.myblog.blog.controller.api;

import com.myblog.blog.dto.ResponseDto;
import com.myblog.blog.model.Role;
import com.myblog.blog.model.User;
import com.myblog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("save");
        userService.register(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
