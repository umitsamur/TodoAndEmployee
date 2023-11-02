package net.rakip.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.rakip.ems.dto.DepartmentDTO;
import net.rakip.ems.service.DepartmentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDto) {
		DepartmentDTO department = departmentService.createDepartment(departmentDto);
		return new ResponseEntity<>(department,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("{id}")
	public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") Long departmentId){
		DepartmentDTO department = departmentService.getDepartmentById(departmentId);
		return ResponseEntity.ok(department);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
		List<DepartmentDTO> departments = departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDTO toUpdatedDepartment){
		DepartmentDTO departmentDto = departmentService.updateDepartment(departmentId, toUpdatedDepartment);
		return ResponseEntity.ok(departmentDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
		departmentService.deleteDepartment(departmentId);
		return ResponseEntity.ok("Department deleted succesfully!");
	}
	
}
