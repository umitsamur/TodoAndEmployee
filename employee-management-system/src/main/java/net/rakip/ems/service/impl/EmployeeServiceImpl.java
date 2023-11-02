package net.rakip.ems.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.rakip.ems.dto.EmployeeDTO;
import net.rakip.ems.entity.Department;
import net.rakip.ems.entity.Employee;
import net.rakip.ems.exception.ResourceNotFoundException;
import net.rakip.ems.mapper.EmployeeMapper;
import net.rakip.ems.service.EmployeeService;
import net.rakip.ems.repository.DepartmentRepository;
import net.rakip.ems.repository.EmployeeRepository;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employee.setFirstName(titleCase(employee.getFirstName()));
        employee.setLastName(titleCase(employee.getLastName()));
        
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
        		.orElseThrow(() -> new ResourceNotFoundException("Department is not exist with id: " + employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id: " + employeeId)); // orElseThrow(Supplier class) burada arrow function uygulanır
                                                                                                                         
        return EmployeeMapper.mapToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDTO(employee)).sorted(Comparator.comparing(EmployeeDTO::getId)).collect(toList());
    }

    @Override
    public EmployeeDTO updateEmplyoee(Long employeeId, EmployeeDTO updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));
        employee.setFirstName(titleCase(updatedEmployee.getFirstName()));
        employee.setLastName(titleCase(updatedEmployee.getLastName()));
        employee.setEmail(updatedEmployee.getEmail());
        
        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
        		.orElseThrow(() -> new ResourceNotFoundException("Department is not exist with id: " + updatedEmployee.getDepartmentId()));
        employee.setDepartment(department);

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).
        		orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));
       
        employeeRepository.delete(employee);
    }

    public String titleCase(String word){
        return word.length() > 0 ? word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase() : null;
    }
}
