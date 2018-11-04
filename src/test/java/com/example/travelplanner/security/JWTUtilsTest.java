package com.example.travelplanner.security;

import com.example.travelplanner.domain.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class JWTUtilsTest {

    private Logger logger = LoggerFactory.getLogger(JWTUtilsTest.class);

    @Test
    public void getUsernameFromToken() {
        String token = JWTUtils.generateToken(new User(1L, "username", "password", "USER"));
        long id = JWTUtils.getAuthenticatedUserId(token, JWTUtils.ACCESS_SECRET_KEY);
        logger.info("User token: " + token);

        assertEquals(1L, id);
    }

    @Test
    public void getDateCreatedFromToken() {
    }

}