package net.rakip.ems.service;

import java.util.List;

import net.rakip.ems.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDto);
    EmployeeDTO getEmployeeById(Long employeeId);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO updateEmplyoee(Long employeeId, EmployeeDTO updatedEmployee);
    void deleteEmployee(Long employeeId);
}
