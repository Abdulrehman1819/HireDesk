package com.example.HireDesk.controller;

import com.example.HireDesk.dto.TenantLoginRequestDTO;
import com.example.HireDesk.dto.TenantResponseDTO;
import com.example.HireDesk.model.Tenant;
import com.example.HireDesk.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/auth")
@RestController
public class TenantController {

@Autowired
TenantService tenantService;

@PostMapping("/signup")
    public ResponseEntity<TenantResponseDTO> signup(@Valid @RequestBody Tenant tenant){
    return ResponseEntity.status(201).body(tenantService.createTenant(tenant));
}
@PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody TenantLoginRequestDTO tenant){
    return  ResponseEntity.status(200).body(tenantService.loginTenant(tenant));
}
}
