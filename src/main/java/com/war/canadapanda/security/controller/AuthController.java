package com.war.canadapanda.security.controller;

import com.war.canadapanda.security.payload.request.LoginPayload;
import com.war.canadapanda.security.payload.request.SignUpPayload;
import com.war.canadapanda.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginPayload loginPayload) {
        return customUserDetailsService.authenticateUser(loginPayload);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpPayload signUpPayload) {
        return customUserDetailsService.registerUser(signUpPayload);
    }

}
