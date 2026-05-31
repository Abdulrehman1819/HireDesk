package com.example.HireDesk.repo;

import com.example.HireDesk.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Integer>  {
Tenant findUserByName(String name);
}
