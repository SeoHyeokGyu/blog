package com.myblog.blog.test;

import com.myblog.blog.model.Role;
import com.myblog.blog.model.User;
import com.myblog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

//
@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    //json 받아서
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser){

        System.out.println(id);
        System.out.println(requestUser.getPassword());
        System.out.println(requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        requestUser.setPassword(requestUser.getPassword());
        requestUser.setEmail(requestUser.getEmail());
        //userRepository.save(requestUser);
        return null;
    }
    @GetMapping("dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 한 페이지당 2건
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUsers = userRepository.findAll(pageable);
        List<User> users = pagingUsers.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // 람다식사용
        User user = userRepository.findById(id).orElseThrow(()->{
                return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
        });
        return user;
    }
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("Id:"+user.getId());
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());
        System.out.println("email:"+user.getEmail());
        System.out.println("role:"+user.getRole());
        System.out.println("createDate:"+user.getCreateDate());

        user.setRole(Role.USER);
        userRepository.save(user);
        return "회원가입 성공";
    }
}
