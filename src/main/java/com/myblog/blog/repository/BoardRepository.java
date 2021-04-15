package com.myblog.blog.repository;

import com.myblog.blog.model.Board;
import com.myblog.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
