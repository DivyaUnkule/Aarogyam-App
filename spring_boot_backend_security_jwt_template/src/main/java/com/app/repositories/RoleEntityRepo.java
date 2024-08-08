package com.app.repositories;

import java.util.Optional;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.RoleEntity;
import com.app.enums.Role;


@Repository
public interface RoleEntityRepo extends JpaRepository<RoleEntity, Long> {
	@Query("select r from RoleEntity r where r.roleName= :roleName")
	Optional<RoleEntity>findByRoleName(@Param("roleName")Role roleName);
	
    Set<RoleEntity> findByRoleNameIn(Set<Role> roles);
    

}
