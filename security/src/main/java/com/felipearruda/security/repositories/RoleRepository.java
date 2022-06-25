package com.felipearruda.security.repositories;

import com.felipearruda.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {

    Role findByPerfil(String perfil);
}
