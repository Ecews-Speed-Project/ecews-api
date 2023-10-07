package com.ihvncr.ihvncrapi.service;


import com.ihvncr.ihvncrapi.exception.TokenRefreshException;
import com.ihvncr.ihvncrapi.model.RefreshToken;
import com.ihvncr.ihvncrapi.model.User;
import com.ihvncr.ihvncrapi.repository.RefreshTokenRepository;
import com.ihvncr.ihvncrapi.repository.UserRepository;
import com.ihvncr.ihvncrapi.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  @Value("${codeusingjava.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;
  private final RefreshTokenRepository refreshTokenRepository;
  private final UserRepository userRepository;
  private final UserUtil userUtil;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(String username) {
    User user = userUtil.getUserByEmail(username);
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setUser(user);
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());
    return refreshTokenRepository.save(refreshToken);

  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }
}
