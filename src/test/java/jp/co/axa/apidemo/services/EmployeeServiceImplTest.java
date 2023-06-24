package jp.co.axa.apidemo.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.exception.EmployeeServiceException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	public void testGetAllEmployees() {
		Assert.assertNotNull(employeeServiceImpl.retrieveEmployees());
	}

	@Test
	public void testGetEmployeeException() {
		long employeeId = 1;
		EmployeeNotFoundException e = assertThrows(EmployeeNotFoundException.class,
                () ->employeeServiceImpl.getEmployee(employeeId));
		Assertions.assertEquals("Employee Id 1 is not exist ", e.getMessage());
	}
	
	@Test
	public void testSaveEmployeeData() {
		Employee employee = new Employee();
		employee.setId((long) 1);
		employee.setName("Name");
		employee.setDepartment("HR");
		Assert.assertNull(employeeServiceImpl.saveEmployee(employee));
	}
	
	@Test
	public void testUpdateEmployeeData() {
		long employeeId=1;
		Employee employee = new Employee();
		employee.setId((long) 1);
		employee.setName("Name");
		employee.setDepartment("HR");
        Optional<Employee> optional1 = Optional.of(employee);
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(optional1);
		Assert.assertNull(employeeServiceImpl.updateEmployee(employee, employeeId));
	}
	
	@Test
	public void testUpdateEmployeeDataException() {
		long employeeId=1;
		Employee employee = new Employee();
		employee.setName("Name");
		employee.setDepartment("HR");
		
		EmployeeNotFoundException e = assertThrows(EmployeeNotFoundException.class,
                () ->employeeServiceImpl.updateEmployee(employee, employeeId));
		Assertions.assertEquals("Employee Id 1 is not exist ",e.getMessage());
	}
	
	@Test
	public void testDeleteEmployeeData() {
		long employeeId=1;
		Employee employee = new Employee();
		employee.setId((long) 1);
		employee.setName("Name");
		employee.setDepartment("HR");
		
		  Optional<Employee> optional1 = Optional.of(employee);
			Mockito.when(employeeRepository.findById(employeeId)).thenReturn(optional1);
		Assert.assertNotNull(employeeServiceImpl.deleteEmployee(employeeId));
	}
	
	
}
