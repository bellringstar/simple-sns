package com.project.sns.service;

import com.project.sns.exception.SnsApplicationException;
import com.project.sns.model.User;
import com.project.sns.model.entity.UserEntity;
import com.project.sns.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public User join(String username, String password) {
        userEntityRepository.findByUsername(username).ifPresent(it -> {
            throw new SnsApplicationException();
        });

        userEntityRepository.save(new UserEntity());
        return new User();
    }

    public String login(String username, String password){
        UserEntity userEntity = userEntityRepository.findByUsername(username).orElseThrow(SnsApplicationException::new);

        if(!userEntity.getPassword().equals(password)){
            throw new SnsApplicationException();
        }

        return "";
    }
}

