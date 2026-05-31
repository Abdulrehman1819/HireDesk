package com.example.HireDesk.controller;

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
    public ResponseEntity<Tenant> signup(@Valid @RequestBody Tenant tenant){

    tenantService.createTenant(tenant) ;
    return ResponseEntity.status(201).body(tenant);
}

}
