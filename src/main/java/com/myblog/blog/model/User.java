package com.myblog.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 50)
    private  String email;

    //DB에서 ENUM 타입이 없기 때문
    @Enumerated(EnumType.STRING)
    private Role role; //Enum 사용해함, admin,user,manager

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;

}
