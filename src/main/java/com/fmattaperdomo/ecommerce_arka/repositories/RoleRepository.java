package com.fmattaperdomo.ecommerce_arka.repositories;

import com.fmattaperdomo.ecommerce_arka.entities.AppRole;
import com.fmattaperdomo.ecommerce_arka.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}

