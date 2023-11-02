package net.rakip.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.rakip.ems.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String role_name);
	
}
