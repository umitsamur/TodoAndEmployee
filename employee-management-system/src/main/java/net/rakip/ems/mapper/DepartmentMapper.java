package net.rakip.ems.mapper;

import net.rakip.ems.dto.DepartmentDTO;
import net.rakip.ems.entity.Department;

public class DepartmentMapper {
	// convert department jpa entity into department dto
	public static DepartmentDTO mapToDepartmentDTO(Department department) {
		return new DepartmentDTO(department.getId(), department.getDepartmentName(), department.getDepartmentDescription());
	}
	
	// convert department dto into department jpa entity
	public static Department mapToDepartment(DepartmentDTO departmentDTO) {
		return new Department(departmentDTO.getId(), departmentDTO.getDepartmentName(), departmentDTO.getDepartmentDescription());
	}
}
