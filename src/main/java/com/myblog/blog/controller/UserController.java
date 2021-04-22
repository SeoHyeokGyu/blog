package com.myblog.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myblog.blog.model.KakaoProfile;
import com.myblog.blog.model.OAuthToken;
import com.myblog.blog.model.User;
import com.myblog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


//미인증 사용자들 접속 경로 auth/**
//
@Controller
public class UserController {

    @Value("${kakao.key}")
    private String kakaoKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","9597e89b96cfd76bdc6d916bca4275ae");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code",code);

        // header + body
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,headers);

        // http request
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        OAuthToken oauthToken = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
             oauthToken = objectMapper.readValue(responseEntity.getBody(),OAuthToken.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        RestTemplate restTemplate2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        // header + body
        HttpEntity<MultiValueMap<String,String>> kakaoProfileReauest2 = new HttpEntity<>(headers2);

        // http request
        ResponseEntity<String> responseEntity2 = restTemplate2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,kakaoProfileReauest2,String.class);

        KakaoProfile kakaoProfile = null;
        ObjectMapper objectMapper2 = new ObjectMapper();
        try {
            kakaoProfile = objectMapper2.readValue(responseEntity2.getBody(),KakaoProfile.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(kakaoProfile.getId());
        System.out.println(kakaoProfile.getKakao_account().getEmail());

        System.out.println(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println(kakaoProfile.getKakao_account().getEmail());
        System.out.println(kakaoKey);


        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                .password(kakaoKey)
                .email(kakaoProfile.getKakao_account().getEmail())
                .oauth("kakao")
                .build();

        User originUser = userService.findUser(kakaoUser.getUsername());
        System.out.println(kakaoUser.getUsername());

        if(originUser.getUsername() == null){
            System.out.println("신규 회원입니다. 회원가입 진행합니다.");
            userService.register(kakaoUser);
        }
        System.out.println("Login 진행합니다.");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),kakaoKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }
}
