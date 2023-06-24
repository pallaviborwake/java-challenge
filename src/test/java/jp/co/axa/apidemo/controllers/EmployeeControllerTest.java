/**
 * 
 */
package jp.co.axa.apidemo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jp.co.axa.apidemo.constants.ErrorCode;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.ApiException;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.exception.EmployeeServiceException;
import jp.co.axa.apidemo.services.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Test
	public void testGetAllEmployees() {
		Employee employee = new Employee();
		employee.setName("Name");
		employee.setDepartment("HR");
		List<Employee> employeesList = new ArrayList<>();
		employeesList.add(employee);
		Mockito.when(employeeService.retrieveEmployees()).thenReturn(employeesList);

		Assert.assertNotNull(employeeController.getAllEmployees());
	}

	@Test
	public void testGetEmployeeDataId() {
		long employeeId = 1;
		Employee employee = new Employee();
		employee.setName("Name");
		employee.setDepartment("HR");
		Mockito.when(employeeService.getEmployee(employeeId)).thenReturn(employee);

		Assert.assertNotNull(employeeController.getEmployeeDataId(employeeId));
	}

	@Test
	public void testGetEmployeeDataIdException() {
		long employeeId = 1;
		Employee employee = new Employee();
		employee.setName("Name");
		employee.setDepartment("HR");
		Mockito.when(employeeService.getEmployee(employeeId)).thenThrow(EmployeeNotFoundException.class);

//		try {
//			employeeController.getEmployeeDataId(employeeId);
//		} catch (EmployeeServiceException e) {
//			Assertions.assertEquals(null, e.getMessage());
//
//		}
		
		EmployeeServiceException e = assertThrows(EmployeeServiceException.class,
                () -> employeeController.getEmployeeDataId(employeeId));
		Assertions.assertEquals(null, e.getMessage());

	}

	@Test
	public void testSaveEmployeeData() {
		Employee employee = new Employee();
		employee.setName("Name");
		employee.setDepartment("HR");
		Mockito.when(employeeService.saveEmployee(employee)).thenReturn(employee);
		Assert.assertNotNull(employeeController.saveEmployeeData(employee));
	}

	@Test
	public void testUpdateEmployeeData() {
		long employeeId = 1;
		Employee employee = new Employee();
		employee.setName("Name");
		employee.setDepartment("HR");
		Mockito.when(employeeService.updateEmployee(employee, employeeId)).thenReturn(employee);
		Assert.assertNotNull(employeeController.updateEmployeeData(employee, employeeId));
	}

	@Test
	public void testUpdateEmployeeDataException() {
		long employeeId = 1;
		Employee employee = new Employee();
		employee.setId((long) 10);
		employee.setName("Name");
		employee.setDepartment("HR");
		Mockito.when(employeeService.updateEmployee(employee, employeeId)).thenThrow(EmployeeNotFoundException.class);
//		try {
//			employeeController.updateEmployeeData(employee, employeeId);
//		} catch (EmployeeServiceException e) {
//			Assertions.assertEquals(null, e.getMessage());
//
//		}
		
		EmployeeServiceException e = assertThrows(EmployeeServiceException.class,
                () -> employeeController.updateEmployeeData(employee, employeeId));
		Assertions.assertEquals(null, e.getMessage());

	}

	@Test
	public void testDeleteEmployeeData() {
		long employeeId = 1;
		Employee employee = new Employee();
		employee.setName("Name");
		employee.setDepartment("HR");
		Mockito.when(employeeService.deleteEmployee(employeeId)).thenReturn("Sucess");
		Assert.assertNotNull(employeeController.deleteEmployeeData(employeeId));
	}
}
