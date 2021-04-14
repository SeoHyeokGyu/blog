package com.myblog.blog.controller;

import com.myblog.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {



    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principalDetail){
        System.out.println("로그인 사용자"+principalDetail.getUsername());
        return "index";
    }
}
