package net.rakip.ems.service;

import java.util.List;

import net.rakip.ems.dto.DepartmentDTO;

public interface DepartmentService {
	DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
	DepartmentDTO getDepartmentById(Long departmentId);
	List<DepartmentDTO> getAllDepartments();
	DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);
	void deleteDepartment(Long departmentId);
}
