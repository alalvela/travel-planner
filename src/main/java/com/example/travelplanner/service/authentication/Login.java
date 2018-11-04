package com.example.travelplanner.service.authentication;

import com.example.travelplanner.controller.ExceptionController;
import com.example.travelplanner.domain.User;
import com.example.travelplanner.exception.PasswordInvalidException;
import com.example.travelplanner.exception.UsernameInvalidException;
import com.example.travelplanner.repository.UserRepository;
import com.example.travelplanner.security.JWTUtils;
import com.example.travelplanner.service.authentication.data.LoginData;
import com.example.travelplanner.service.authentication.data.LoginResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Login {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);


    @Autowired
    public Login(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResult execute(LoginData data) {

        User user = userRepository.findByUsername(data.getUsername()).orElseThrow(UsernameInvalidException::new);

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            throw new PasswordInvalidException();
        }

        return new LoginResult(user.getId(), JWTUtils.generateToken(user));
    }


}
