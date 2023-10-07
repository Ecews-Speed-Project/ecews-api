package com.ihvncr.ihvncrapi.service;

import com.ihvncr.ihvncrapi.model.Role;
import com.ihvncr.ihvncrapi.payload.response.MessageResponse;
import com.ihvncr.ihvncrapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public ResponseEntity<?> createNewRole(Role role) {
        Role newRole;
        try {
            newRole = roleRepository.save(role);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: role already exists"));
        }
        return ResponseEntity.ok(newRole);
    }

    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public ResponseEntity<?> update(Role request) {
        Role updatedRole;
        try {
            Role role = roleRepository.findById(request.getId()).orElse(null);
            assert role != null;
            role.setRoleName(request.getRoleName());
            role.setRoleDescription(request.getRoleDescription());
            updatedRole = roleRepository.save(role);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: role already exists"));
        }
        return ResponseEntity.ok(updatedRole);
    }
}
