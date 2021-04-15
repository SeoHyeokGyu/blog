package com.myblog.blog.controller;

import com.myblog.blog.config.auth.PrincipalDetail;
import com.myblog.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("board",boardService.boardList(pageable));
        return "index";
    }


}
