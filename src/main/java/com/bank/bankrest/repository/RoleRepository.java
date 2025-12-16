package com.bank.bankrest.repository;

import com.bank.bankrest.domain.model.Role;
import com.bank.bankrest.domain.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
