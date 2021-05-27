package com.quintoimpacto.turismomendoza.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quintoimpacto.turismomendoza.app.entity.Role;

@Repository
public interface RoleRepositorio extends JpaRepository<Role,Long> {

	

}
