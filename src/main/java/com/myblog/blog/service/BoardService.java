package com.myblog.blog.service;

import com.myblog.blog.model.Board;
import com.myblog.blog.model.Role;
import com.myblog.blog.model.User;
import com.myblog.blog.repository.BoardRepository;
import com.myblog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board,User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }


}
