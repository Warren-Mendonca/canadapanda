package com.war.canadapanda.users.controller;

import com.war.canadapanda.core.model.db.UserInfo;
import com.war.canadapanda.security.payload.response.UserPrincipal;
import com.war.canadapanda.security.util.CurrentUser;
import com.war.canadapanda.users.payload.response.UserIdentityAvailability;
import com.war.canadapanda.users.payload.response.UserProfile;
import com.war.canadapanda.users.payload.response.UserSummary;
import com.war.canadapanda.users.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal userPrincipal){
        return userInfoService.getCurrentUser(userPrincipal);
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username){
        return userInfoService.checkUsernameAvailability(username);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email){
        return userInfoService.checkEmailAvailability(email);
    }

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        return userInfoService.getUserProfile(username);
    }

}
