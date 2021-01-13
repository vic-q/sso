package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@RequestMapping(value = "/api")
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }


    @GetMapping(value = "/auth")
    public void auth(String username, String pwd, HttpServletResponse response) throws IOException {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        subject.login(token);

        final String callbackUrl = "http://127.0.0.1:8100/api/callback";

        String ticket = UUID.randomUUID().toString();

        response.sendRedirect(callbackUrl + "?ticket=" + ticket);

    }

}
