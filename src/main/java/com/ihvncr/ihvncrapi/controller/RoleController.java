package com.ihvncr.ihvncrapi.controller;

import com.ihvncr.ihvncrapi.model.Role;
import com.ihvncr.ihvncrapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/roles")
    public @ResponseBody Iterable<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping({"role/create"})
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> createNewRole(@RequestBody @Valid Role role) {
        //get current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return roleService.createNewRole(role);
    }

    @RequestMapping("/role/update")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?>  update(@RequestBody @Valid  Role request) {
        return ResponseEntity.ok(roleService.update(request));
    }

}
