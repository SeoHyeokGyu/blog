package com.myblog.blog.service;

import com.myblog.blog.model.User;
import com.myblog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void register(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true) //정합성
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());

    }

}
