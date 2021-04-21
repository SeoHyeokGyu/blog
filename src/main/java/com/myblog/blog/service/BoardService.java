package com.myblog.blog.service;

import com.myblog.blog.dto.ReplySaveRequestDto;
import com.myblog.blog.model.Board;
import com.myblog.blog.model.Reply;
import com.myblog.blog.model.Role;
import com.myblog.blog.model.User;
import com.myblog.blog.repository.BoardRepository;
import com.myblog.blog.repository.ReplyRepository;
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
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void write(Board board,User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board boardDetail(int id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세 실패 : 아이디 찾을 수 없음");
                });
    }

    @Transactional
    public void delete(int id){
         boardRepository.deleteById(id);
    }

    @Transactional
    public void update(int id,Board requestBoard){
        Board board = boardRepository.findById(id).orElseThrow(()->{
           return new IllegalArgumentException("글 수정 실패");
        });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    @Transactional
    public void replyWrite(ReplySaveRequestDto replySaveRequestDto){

        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
            return new IllegalArgumentException("댓글 작성 실패 : 유저 id ");
        });
        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
            return new IllegalArgumentException("댓글 작성 실패 : 게시글 id ");
        });

        Reply reply = Reply.builder().user(user).board(board).content(replySaveRequestDto.getContent())
                .build();


        replyRepository.save(reply);
    }
}
