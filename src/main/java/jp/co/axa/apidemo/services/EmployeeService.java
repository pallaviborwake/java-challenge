package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

import org.springframework.http.HttpStatus;

public interface EmployeeService {

    public List<Employee> retrieveEmployees();

    public Employee getEmployee(Long employeeId);

    public Employee saveEmployee(Employee employee);

    public String deleteEmployee(Long employeeId);

    public Employee updateEmployee(Employee employee, Long employeeId);
}