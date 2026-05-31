package com.example.HireDesk.service;

import com.example.HireDesk.Util.JwtUtil;
import com.example.HireDesk.Util.Mapper;
import com.example.HireDesk.dto.TenantLoginRequestDTO;
import com.example.HireDesk.dto.TenantResponseDTO;
import com.example.HireDesk.model.Tenant;
import com.example.HireDesk.repo.TenantRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    @Autowired
    private JwtUtil jwtUtil;
    private Mapper mapper = new Mapper();
    public TenantResponseDTO createTenant( Tenant tenant) {
    tenant.setAdminPassword(passwordEncoder.encode(tenant.getAdminPassword()));
    tenantRepository.save(tenant);
    return mapper.convertTenantToTenantResponseDTO(tenant);
    }

    public String loginTenant(@Valid TenantLoginRequestDTO tenantRequest) {
        Tenant tenant=tenantRepository.findByAdminEmail(tenantRequest.getAdminEmail());
        if(tenant!=null){
            if(!passwordEncoder.matches(tenantRequest.getAdminPassword(),tenant.getAdminPassword())){
                return "Invalid email or password";
            }
        }
        else{
            return "Invalid email or password";
        }


    String token=jwtUtil.generateToken(tenant);
    return token;
    }
}
