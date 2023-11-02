package net.rakip.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.rakip.ems.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
