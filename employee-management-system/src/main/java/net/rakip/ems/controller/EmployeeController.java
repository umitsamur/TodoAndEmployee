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
import org.springframework.web.bind.annotation.RestController;

import net.rakip.ems.dto.EmployeeDTO;
import net.rakip.ems.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // Build Add Employee REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDto){
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDTO);
    }

    // Build Get All Employee REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Build Update Employee REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDTO updatedEmployee){
        EmployeeDTO employeeDto = employeeService.updateEmplyoee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    // Build Delete Employee REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted succesfully!.");
    }
}
