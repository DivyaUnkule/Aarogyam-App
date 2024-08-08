package com.app.repositories;

import com.app.entities.ExerciseYoga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseYogaRepository extends JpaRepository<ExerciseYoga, Long> {
	
	@Query("Select e from ExerciseYoga e where e.role.id = :roleId")
	ExerciseYoga findByRoleId(@Param("roleId") Long roleId);
    
}
