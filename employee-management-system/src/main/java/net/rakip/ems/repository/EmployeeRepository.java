package net.rakip.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.rakip.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
