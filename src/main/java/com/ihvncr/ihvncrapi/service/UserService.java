package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.exception.ResourceNotFoundException;
import com.ihvncr.ihvncrapi.exception.UserNotFoundException;
import com.ihvncr.ihvncrapi.interfaces.IUser;
import com.ihvncr.ihvncrapi.model.Role;
import com.ihvncr.ihvncrapi.model.State;
import com.ihvncr.ihvncrapi.model.User;
import com.ihvncr.ihvncrapi.payload.request.UserRequest;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.repository.RoleRepository;
import com.ihvncr.ihvncrapi.repository.StateRepository;
import com.ihvncr.ihvncrapi.repository.UserRepository;
import com.ihvncr.ihvncrapi.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUser {
    private final  UserRepository userRepository;
    private final  RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;
    private final StateRepository stateRepository;

    public ResponseEntity<MessageResponse> create(UserRequest request) {
        User newUser = userRepository.findByUserEmail(request.getEmail()).orElse(null);
        if (newUser == null) {
            long userRole = request.getRole();
            Role role = roleRepository.findById(userRole).orElseThrow(()-> new ResourceNotFoundException("Role not found"));
            State state = stateRepository.findByStateName(request.getState()).orElseThrow(()-> new ResourceNotFoundException("State not found"));
            newUser = new User();
            newUser.setUserName(request.getUsername());
            newUser.setUserFirstName(request.getFirstName());
            newUser.setUserLastName(request.getLastName());
            newUser.setUserEmail(request.getEmail());
            newUser.setState(state);
            newUser.setUserPassword(getEncodedPassword(request.getPassword()));
            newUser.setRole(new HashSet<>(Collections.singleton(role)));
            userRepository.save(newUser);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } else
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Username or email is already taken!"));

    }
    public User update(UserRequest request) {
        User user = userRepository.findById(request.getId()).orElse(null);
        if (user != null) {
            user.setUserName(request.getUsername());
            user.setUserFirstName(request.getFirstName());
            user.setUserLastName(request.getLastName());
            user.setUserEmail(request.getEmail());
            user.setUserPassword(getEncodedPassword(request.getPassword()));
            return userRepository.save(user);
        } else
            throw new UserNotFoundException("User not found");
    }
    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User getUserByUsername(String username) {
        return userUtil.getUserByUsername(username);
    }

    @Override
    public List<User> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> pagedResult = userRepository.findAll(paging);
        return pagedResult.toList();
    }

    public List<User> getAllUsers(int pageNo, int pageSize) {
        return findPaginated(pageNo, pageSize);
    }

    public void createSuperAdmin(UserRequest userRequest) throws ResourceNotFoundException {
        User user = userRepository.findByUserEmail(userRequest.getEmail())
                .orElse(null);
        if (user == null) {
            Role role = roleRepository.findById(userRequest.getRole())
                    .orElseThrow(()-> new ResourceNotFoundException("Role not found"));
            user = User.builder()
                    .userEmail(userRequest.getEmail())
                    .userFirstName(userRequest.getFirstName())
                    .userPassword(getEncodedPassword(userRequest.getPassword()))
                    .userName(userRequest.getUsername())
                    .userLastName(userRequest.getLastName())
                    .role(new HashSet<>(Collections.singleton(role)))
                    .build();
            userRepository.save(user);
            log.info("Super admin created successfully");
        }
    }
}
