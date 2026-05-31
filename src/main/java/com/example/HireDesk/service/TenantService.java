package com.example.HireDesk.service;

import com.example.HireDesk.model.Tenant;
import com.example.HireDesk.repo.TenantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TenantService {
    @Autowired
    TenantRepository tenantRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createTenant(@Valid Tenant tenant) {
    tenant.setAdminPassword(passwordEncoder.encode(tenant.getAdminPassword()));
    tenantRepository.save(tenant);
    }
}
