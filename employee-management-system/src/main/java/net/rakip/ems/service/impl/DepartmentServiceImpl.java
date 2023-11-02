package net.rakip.ems.service.impl;

import java.lang.module.ResolutionException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.rakip.ems.dto.DepartmentDTO;
import net.rakip.ems.entity.Department;
import net.rakip.ems.exception.ResourceNotFoundException;
import net.rakip.ems.mapper.DepartmentMapper;
import net.rakip.ems.repository.DepartmentRepository;
import net.rakip.ems.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	@Override
	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
		Department department = DepartmentMapper.mapToDepartment(departmentDTO);
		department.setDepartmentName(department.getDepartmentName());
		department.setDepartmentDescription(department.getDepartmentDescription());
		Department savedDepartment = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDTO(savedDepartment);
	}
	
	@Override
	public DepartmentDTO getDepartmentById(Long departmenId) {
		Department department = departmentRepository.findById(departmenId)
					.orElseThrow(() -> new ResolutionException("Department is not exist with the given id: " + departmenId));
		return DepartmentMapper.mapToDepartmentDTO(department);
	}
	
	@Override
	public List<DepartmentDTO> getAllDepartments() {
		List<Department> departments = departmentRepository.findAll();
		return departments.stream().map(department -> DepartmentMapper.mapToDepartmentDTO(department))
																.sorted(Comparator.comparing(DepartmentDTO::getId)).collect(Collectors.toList());
	}
	
	@Override
	public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
		Department department = departmentRepository.findById(departmentId)
								.orElseThrow(() -> new ResourceNotFoundException("Department is not exist with the given id: " + departmentId));
		department.setDepartmentName(departmentDTO.getDepartmentName());
		department.setDepartmentDescription(departmentDTO.getDepartmentDescription());
		
		Department toUpdatedDepartment = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDTO(toUpdatedDepartment);
	}
	
	@Override
	public void deleteDepartment(Long departmentId) {
		Department department = departmentRepository.findById(departmentId)
						.orElseThrow(() -> new ResourceNotFoundException("Department is not exist with the given id: " + departmentId));
		departmentRepository.delete(department);
	}

	public String titleCase(String word){
		return word.length() > 0 ? word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase() : null;
    }
	
}
