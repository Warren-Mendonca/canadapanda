package com.war.canadapanda.users.service;

import com.war.canadapanda.core.model.UserInfoModel;
import com.war.canadapanda.core.repositories.UserInfoRepository;
import com.war.canadapanda.security.payload.response.UserPrincipal;
import com.war.canadapanda.users.payload.response.UserIdentityAvailability;
import com.war.canadapanda.users.payload.response.UserProfile;
import com.war.canadapanda.users.payload.response.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getEmail());
    }

    public UserIdentityAvailability checkUsernameAvailability(String username) {
        return new UserIdentityAvailability(!userInfoRepository.existsByUsername(username));
    }

    public UserIdentityAvailability checkEmailAvailability(String email) {
        return new UserIdentityAvailability(!userInfoRepository.existsByEmail(email));
    }

    public UserProfile getUserProfile(String username) {
        UserInfoModel user = userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username : " + username));
        return new UserProfile(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
