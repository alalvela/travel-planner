package com.example.travelplanner.controller;

import com.example.travelplanner.domain.User;
import com.example.travelplanner.model.dto.UserDTO;
import com.example.travelplanner.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
@Api(value = "authentication")
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> login(@ApiParam(value = "User object to be authenticated", required = true) @RequestBody UserDTO userDto) throws NotFoundException {
        User user = userService.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());

        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    public ResponseEntity logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(null);
    }

}
