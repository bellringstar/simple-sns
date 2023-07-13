package com.project.sns.controller;

import com.project.sns.controller.request.UserJoinRequest;
import com.project.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public void join(@RequestBody UserJoinRequest request) {
        userService.join(request.getUsername(), request.getPassword());
    }
}
