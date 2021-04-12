package com.myblog.blog.repository;

import com.myblog.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    //login function, JPA Naming
    User findByUsernameAndPassword(String username,String password);

//    @Query(value = "SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery = true)
//    User login(String username,String password);
}
