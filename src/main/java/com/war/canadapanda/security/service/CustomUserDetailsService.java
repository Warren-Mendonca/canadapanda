package com.war.canadapanda.security.service;

import com.war.canadapanda.core.model.UserInfoModel;
import com.war.canadapanda.core.payload.response.MessageResponse;
import com.war.canadapanda.core.repositories.UserInfoRepository;
import com.war.canadapanda.security.model.RoleName;
import com.war.canadapanda.security.payload.request.LoginPayload;
import com.war.canadapanda.security.payload.request.SignUpPayload;
import com.war.canadapanda.security.payload.response.JwtResponse;
import com.war.canadapanda.security.payload.response.UserPrincipal;
import com.war.canadapanda.security.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoModel userInfoModel = userInfoRepository.findByUsername(username)
                                    .orElseThrow(() -> new UsernameNotFoundException("No user found with username : " + username));

        return UserPrincipal.create(userInfoModel);
    }

    public UserDetails loadUserById(String id){
        UserInfoModel user = userInfoRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }

    public ResponseEntity<?> authenticateUser(LoginPayload loginPayload) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPayload.getUsername(),
                        loginPayload.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    public ResponseEntity<?> registerUser(SignUpPayload signUpPayload) {
        if(userInfoRepository.existsByUsername(signUpPayload.getUsername())) {
            return new ResponseEntity<>(new MessageResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userInfoRepository.existsByEmail(signUpPayload.getEmail())) {
            return new ResponseEntity<>(new MessageResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        UserInfoModel userInfoModel = new UserInfoModel(signUpPayload.getUsername(), signUpPayload.getEmail(), signUpPayload.getFirstName(), signUpPayload.getLastName());
        userInfoModel.setPassword(passwordEncoder.encode(signUpPayload.getPassword()));
        userInfoModel.setRoles(Collections.singletonList(RoleName.ROLE_USER.toString()));

        UserInfoModel result = userInfoRepository.save(userInfoModel);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new MessageResponse(true, "UserInfo registered successfully"));
    }
}
