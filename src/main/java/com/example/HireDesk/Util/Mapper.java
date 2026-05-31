package com.example.HireDesk.Util;

import com.example.HireDesk.dto.TenantResponseDTO;
import com.example.HireDesk.model.Tenant;

public class Mapper {

    public TenantResponseDTO convertTenantToTenantResponseDTO(Tenant tenant) {
        TenantResponseDTO tenantResponseDTO = new TenantResponseDTO();
        tenantResponseDTO.setTenantName(tenant.getAdminName());
        tenantResponseDTO.setAdminEmail(tenant.getAdminEmail());
        tenantResponseDTO.setCompanyName(tenant.getCompanyName());
        tenantResponseDTO.setRole(tenant.getRole());
        return tenantResponseDTO;
    }
}
