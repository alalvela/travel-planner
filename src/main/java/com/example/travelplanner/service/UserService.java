package com.example.travelplanner.service;

import com.example.travelplanner.domain.User;
import javassist.NotFoundException;

public interface UserService {

    public User findByUsernameAndPassword(String username, String password) throws NotFoundException;
}
