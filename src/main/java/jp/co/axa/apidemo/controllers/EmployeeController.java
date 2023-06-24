package jp.co.axa.apidemo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeServiceException;
import jp.co.axa.apidemo.helper.ResponseProvider;
import jp.co.axa.apidemo.services.EmployeeService;

@RestController
@Api(value = "Employee Controller", tags = "Employee Controller")
@RequestMapping("/api/v1")
public class EmployeeController {
	
    private static final Logger Log = LoggerFactory.getLogger(EmployeeController.class);

    /*
     * Set the employee service
     */
    
    @Autowired
    private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/*
	 * This method return all employee deatils which exist.
	 * @PreAuthorize only Admin can access this method
	 * @param header token
	 * @return Employees list
	 */  
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "get all employees data")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }   
    
    /*
	 * This method return specific employee deatils by its Id .
	 * @PreAuthorize only Admin can access this method
	 * @param header token
	 * @param EmployeeId
	 * @return Employees object
	 * @EmployeeServiceException occured when employee id is not exist
	 */   
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "get data by employee id",response=Employee.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Id not found"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @GetMapping("/employees/{employeeId}")
    public Employee  getEmployeeDataId(
    		@ApiParam(value= "EmployeeId", required= true)
    		@PathVariable(name="employeeId") final Long employeeId)
    	 { 
    	   try {              
              return employeeService.getEmployee(employeeId);
           } catch (EmployeeServiceException ex) {
        	   Log.error("Error Occuerd while feching employee data by id", ex.getMessage());
               throw ex;
           }
    }
    
    /*
   	 * This method create new employee object  .
   	 * @PreAuthorize  Admin or User can access this method
   	 * @param header token
   	 * @param employee object
   	 * @return Employees object
   	 */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiOperation(value = "save employees",response=Employee.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @PostMapping(value = "/employees", consumes = "application/json", produces = "application/json")
    public Employee saveEmployeeData(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    
    /*
 	 * This method update specific employee deatils by its Id .
   	 * @PreAuthorize only  Admin can access this method
 	 * @param header token
 	 * @param EmployeeId
 	 * @return Employees object
 	 * @EmployeeServiceException occured when employee id is not exist
 	 */  
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "update employees",response=Employee.class)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses({
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Id not found")})
    @PutMapping(value = "/employees/{employeeId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?>  updateEmployeeData(
    		@ApiParam(value= "EmployeeId", required= true)
    		@RequestBody Employee employee,
    		@PathVariable(name="employeeId") final Long employeeId) {
    	try {
    		 Employee employee1 =  employeeService.updateEmployee(employee, employeeId);
    	        return ResponseProvider.success(employee1);
    	}catch (EmployeeServiceException ex) {
    		Log.error("Error Occuerd while updating employee data", ex.getMessage());
            throw ex;
        }
    }
    
    
    /*
   	 * This method delete employee object by its id .
   	 * @PreAuthorize  only Admin can access this method
   	 * @param header token
   	 * @param employeeId 
   	 * @return String meassage 
   	 */
    @PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "delete employees")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiResponses({ @ApiResponse(code = 500, message = "Something went wrong"),
			@ApiResponse(code = 422, message = "Id not found"), @ApiResponse(code = 401, message = "Unauthorized") })
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployeeData(
			@ApiParam(value = "EmployeeId", required = true) @PathVariable (name="employeeId") final Long employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
}
