package jp.co.axa.apidemo.services;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
		try {
			employeeServiceImpl.getEmployee(employeeId);
		} catch (EmployeeServiceException e) {
			Assertions.assertEquals("Employee Id 1 is not exist ", e.getMessage());

		}

	}

}
