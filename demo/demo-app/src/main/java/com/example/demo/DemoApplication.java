package com.example.demo;

import com.google.common.collect.Lists;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.buji.pac4j.token.Pac4jToken;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@RequestMapping(value = "/api")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @GetMapping(value = "/callback")
    public String callback(String ticket) {
        Subject subject = SecurityUtils.getSubject();

        // 验证ticket合法性
        // ticket 解析出username
        // 根据username获取权限

        CommonProfile profile = new CommonProfile();
        profile.addAttribute("ticket", ticket);
        Pac4jToken pac4jToken = new Pac4jToken(Lists.newArrayList(profile), false);
        subject.login(pac4jToken);

        System.out.println("ticket=" + ticket);

        return ticket;
    }

    @GetMapping(value = "/data")
    public String data() {
        return "data";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

}
