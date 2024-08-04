package com.app.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.RoleEntity;
import com.app.enums.Role;
@Repository
public interface RoleEntityRepo extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByRoleName(Role role);
	Set<RoleEntity> findByRoleNameIn(Set<Role> roles);

}