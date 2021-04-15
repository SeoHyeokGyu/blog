package com.myblog.blog.controller.api;

import com.myblog.blog.config.auth.PrincipalDetail;
import com.myblog.blog.dto.ResponseDto;
import com.myblog.blog.model.Board;
import com.myblog.blog.model.User;
import com.myblog.blog.service.BoardService;
import com.myblog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintStream;


@RestController
public class BoardApiController {


    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail){
        boardService.write(board, principalDetail.getUser());


        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
