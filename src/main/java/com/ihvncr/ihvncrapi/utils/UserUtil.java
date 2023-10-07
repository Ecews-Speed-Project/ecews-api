package com.ihvncr.ihvncrapi.utils;

import com.ihvncr.ihvncrapi.model.User;
import com.ihvncr.ihvncrapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

@Service
@RequiredArgsConstructor
public class UserUtil {
    private final UserRepository userRepository;

    public User getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String loggedInUserUserName = userDetails.getUsername();
        return getUserByEmail(loggedInUserUserName);
    }

    @Cacheable(value = "user")
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByUserEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User with email " + email + " not found"));
    }
}
