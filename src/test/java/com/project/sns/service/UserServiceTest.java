package com.project.sns.service;

import com.project.sns.exception.SnsApplicationException;
import com.project.sns.fixture.UserEntityFixture;
import com.project.sns.model.entity.UserEntity;
import com.project.sns.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityRepository userEntityRepository;


    @DisplayName("회원가입 성공")
    @Test
    void signUpSuccess(){

        String username = "user";
        String password = "1234";

        UserEntity fixture = UserEntityFixture.get(username, password);


        when(userEntityRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(userEntityRepository.save(any())).thenReturn(Optional.of(fixture));

        Assertions.assertDoesNotThrow(() -> userService.join(username, password));

    }

    @DisplayName("회원가입 실패 - 동일 username 존재")
    @Test
    void signUpFailUsername(){

        String username = "user";
        String password = "1234";

        UserEntity fixture = UserEntityFixture.get(username, password);


        when(userEntityRepository.findByUsername(username)).thenReturn(Optional.of(fixture));
        when(userEntityRepository.save(any())).thenReturn(Optional.of(fixture));

        Assertions.assertThrows(SnsApplicationException.class,() -> userService.join(username, password));

    }

    @DisplayName("로그인 성공")
    @Test
    void loginSuccess(){

        String username = "user";
        String password = "1234";

        UserEntity fixture = UserEntityFixture.get(username, password);

        when(userEntityRepository.findByUsername(username)).thenReturn(Optional.of(fixture));

        Assertions.assertDoesNotThrow(() -> userService.login(username, password));

    }

    @DisplayName("로그인 실패 - username 존재하지 않는다.")
    @Test
    void loginFailUsername(){

        String username = "user";
        String password = "1234";

        when(userEntityRepository.findByUsername(username)).thenReturn(Optional.empty());

        Assertions.assertThrows(SnsApplicationException.class,() -> userService.login(username, password));

    }

    @DisplayName("로그인 실패 - password가 틀린 경우")
    @Test
    void loginFailPassword(){

        String username = "user";
        String password = "1234";
        String wrongPassword = "wrongPassword";
        UserEntity fixture = UserEntityFixture.get(username, password);


        when(userEntityRepository.findByUsername(username)).thenReturn(Optional.of(fixture));

        Assertions.assertThrows(SnsApplicationException.class,() -> userService.login(username, wrongPassword));

    }


}
