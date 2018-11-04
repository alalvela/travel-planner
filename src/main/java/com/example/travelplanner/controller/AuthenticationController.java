package com.example.travelplanner.controller;

import com.example.travelplanner.domain.User;
import com.example.travelplanner.model.dto.UserDTO;
import com.example.travelplanner.service.authentication.Login;
import com.example.travelplanner.service.authentication.data.LoginData;
import com.example.travelplanner.service.authentication.data.LoginResult;
import com.example.travelplanner.service.user.GetUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Api(value = "authentication")
public class AuthenticationController {


    private GetUser getUser;
    private Login login;

    @Autowired
    public AuthenticationController(GetUser getUser, Login login) {
        this.getUser = getUser;
        this.login = login;
    }

    @PostMapping
    public ResponseEntity<LoginResult> login(@ApiParam(value = "User object to be authenticated", required = true) @RequestBody UserDTO userDto) throws NotFoundException {
        LoginResult loginResult = login.execute(new LoginData(userDto.getUsername(), userDto.getPassword()));
        return ResponseEntity.ok(loginResult);
    }

    @DeleteMapping
    public ResponseEntity logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(null);
    }

}
