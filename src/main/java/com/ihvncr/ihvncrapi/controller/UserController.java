package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.model.User;
import com.ihvncr.ihvncrapi.payload.request.UserRequest;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('admin')")
    public @ResponseBody Iterable<User> getAllUsers(@RequestParam(value = "page") int pageNo, @RequestParam(value = "per_page") int pageSize) {
        return userService.getAllUsers(pageNo, pageSize);
    }

    @PostMapping({"/register"})
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> createNewUser(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @RequestMapping("/user/update")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<User> updateUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(request));
    }
}
