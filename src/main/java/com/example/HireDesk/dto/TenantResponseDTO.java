package com.example.HireDesk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantResponseDTO {

    private String tenantName;
    private String adminEmail;
    private String companyName;
    private String role;
}
