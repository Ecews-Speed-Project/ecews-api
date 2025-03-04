package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.exception.TokenRefreshException;
import com.ihvncr.ihvncrapi.model.RefreshToken;
import com.ihvncr.ihvncrapi.model.Token;
import com.ihvncr.ihvncrapi.payload.request.JwtRequest;
import com.ihvncr.ihvncrapi.payload.request.RefreshTokenRequest;
import com.ihvncr.ihvncrapi.payload.response.JwtResponse;
import com.ihvncr.ihvncrapi.payload.response.TokenRefreshResponse;
import com.ihvncr.ihvncrapi.repository.TokenRepository;
import com.ihvncr.ihvncrapi.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private  final TokenRepository tokenRepository;

    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    public JwtResponse createJwtToken(JwtRequest jwtRequest, boolean isPublic) {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userName);
        String newGeneratedToken =
                (isPublic == true) ?
                        jwtUtil.generatePublicToken(userDetails) :
                        jwtUtil.generateToken(userDetails);
        Token token = new Token();
        token.setId(Long.parseLong("1"));
        token.setToken(newGeneratedToken);
        tokenRepository.save(token);
        return new JwtResponse(refreshToken.getUser(), newGeneratedToken, refreshToken.getToken());
    }

    private void authenticate(String userName, String userPassword) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, userPassword)
            );
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("email/password not correct");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public ResponseEntity<TokenRefreshResponse> refreshToken(RefreshTokenRequest request){
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                   // String token = jwtUtil.generateToken(customUserDetailsService.loadUserByUsername(user.getUserName()));
                    return ResponseEntity.ok(new TokenRefreshResponse("token", requestRefreshToken));
                })
              .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                      "Refresh token is not in database!"));
    }
}
