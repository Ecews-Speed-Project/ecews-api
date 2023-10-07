package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.exception.ResourceNotFoundException;
import com.ihvncr.ihvncrapi.payload.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuperAdminService implements CommandLineRunner {
    private final UserService userService;
    @Value("${super.admin.email}")
    private String email;
    @Value("${super.admin.password}")
    private String password;

    @Override
    public void run(String... args) throws ResourceNotFoundException {
        userService.createSuperAdmin(UserRequest.builder()
                .role(1)
                .email(email)
                .firstName("ECEWS")
                .lastName("Admin")
                .password(password)
                .username("ecewsadmin")
                .build()
        );
        userService.createSuperAdmin(UserRequest.builder()
                .role(3)
                .email("sicentral@ecews.org")
                .firstName("SI")
                .lastName("Central")
                .password(password)
                .username("Central SI")
                .build()
        );
    }
}
