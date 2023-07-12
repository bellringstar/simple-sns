package com.project.sns.fixture;

import com.project.sns.model.User;
import com.project.sns.model.entity.UserEntity;

public class UserEntityFixture {

    public static UserEntity get(String username, String password){
        UserEntity result = new UserEntity();
        result.setId(1L);
        result.setUsername(username);
        result.setPassword(password);

        return result;

    }

}
