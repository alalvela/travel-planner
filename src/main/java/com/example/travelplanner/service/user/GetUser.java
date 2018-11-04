package com.example.travelplanner.service.user;

import com.example.travelplanner.domain.User;
import com.example.travelplanner.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GetUser {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public GetUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User byId(long id) {
        return userRepository.findById(id).get();
    }

    public User byUsernameAndPassword(String username, String password) throws NotFoundException {
        User user = userRepository.findByUsername(username).get();

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new NotFoundException("Wrong password!");
        }

        return user;
    }
}
