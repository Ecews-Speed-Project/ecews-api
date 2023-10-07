package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.payload.request.JwtRequest;
import com.ihvncr.ihvncrapi.payload.request.RefreshTokenRequest;
import com.ihvncr.ihvncrapi.payload.request.UserRequest;
import com.ihvncr.ihvncrapi.payload.response.AppTokenResponse;
import com.ihvncr.ihvncrapi.payload.response.JwtResponse;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.payload.response.TokenRefreshResponse;
import com.ihvncr.ihvncrapi.repository.ArtLineListRepository;
import com.ihvncr.ihvncrapi.repository.TokenRepository;
import com.ihvncr.ihvncrapi.service.AuthService;
import com.ihvncr.ihvncrapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    private final TokenRepository tokenRepository;

    @PostMapping({"/login"})
    public ResponseEntity<JwtResponse> Login(@RequestBody JwtRequest jwtRequest) {
        return new ResponseEntity<>(authService.createJwtToken(jwtRequest, false), HttpStatus.OK);
    }

    @PostMapping({"/login-public"})
    public ResponseEntity<JwtResponse> LoginPublic(@RequestBody JwtRequest jwtRequest) {
        return new ResponseEntity<>(authService.createJwtToken(jwtRequest, true), HttpStatus.OK);
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @PostMapping({"/register"})
    public ResponseEntity<MessageResponse> createNewRole(@RequestBody @Valid UserRequest request) {
        return userService.create(request);
    }

    @PostMapping({"/get-token"})
    public ResponseEntity<?> getCurrentToken() {
        return  ResponseEntity.ok(new AppTokenResponse(tokenRepository.findById(Long.parseLong("1")).get().getToken()));
    }


}
