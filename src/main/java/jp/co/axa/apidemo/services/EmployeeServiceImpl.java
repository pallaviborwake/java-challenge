package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.constants.ErrorCode;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "employee")
public  class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
    
    @Cacheable( value = "employee",key="#employeeId")
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        if (optEmp.isPresent())
        {
        return optEmp.get();
        }
        else {
        	throw new EmployeeNotFoundException("Employee Id"+employeeId +" is not exist ",ErrorCode.NO_DATA);
        }
    }
    @CachePut(value = "employee", key = "#postEmployee.id")
    public Employee saveEmployee(Employee postEmployee) {
        Employee employee = new Employee();
        employee.setName(postEmployee.getName());       
        employee.setDepartment(postEmployee.getDepartment());
        employee.setSalary(postEmployee.getSalary());
        return employeeRepository.save(employee);
    }
    
    @CacheEvict(value = "employee", key = "#employeeId")
    public String deleteEmployee(Long employeeId) {
        if (getEmployee(employeeId) != null) {
            employeeRepository.deleteById(employeeId);
            return "Employee Id data deleted sucssefully";
        }
		return "No employee data found for enterd id";
    }

    @CachePut(value = "employee",key = "#employeeId")
    public Employee updateEmployee(Employee putEmployee, Long employeeId) {
        Employee currentEmployee = getEmployee(employeeId);
        if (currentEmployee != null) {
                currentEmployee.setName(putEmployee.getName());
                currentEmployee.setDepartment(putEmployee.getDepartment());
                currentEmployee.setSalary(putEmployee.getSalary());
                return employeeRepository.save(currentEmployee);

            }else {
            	throw new EmployeeNotFoundException("Employee "+employeeId +"  is not exist "+employeeId,ErrorCode.NO_ID);
            }
        }
    }




   
