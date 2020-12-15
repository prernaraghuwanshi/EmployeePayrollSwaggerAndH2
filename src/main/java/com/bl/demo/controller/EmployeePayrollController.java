package com.bl.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bl.demo.service.IEmployeePayrollService;
import com.bl.demo.model.EmployeeDO;
import com.bl.demo.model.ResponseDo;

@RestController
public class EmployeePayrollController {
	
	@Autowired
	private IEmployeePayrollService employeePayrollService;
	
	@PostMapping(value = "/add")
	public ResponseEntity<ResponseDo> addEmployee(@Valid @RequestBody EmployeeDO emp) {
		return new ResponseEntity<ResponseDo>(employeePayrollService.addEmployee(emp),HttpStatus.OK);
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<EmployeeDO>> getEmployeeList() {
		return new ResponseEntity(employeePayrollService.getEmployeeList(),HttpStatus.OK);
	}
	
	@GetMapping("/get/{empId}")
	public ResponseEntity<ResponseDo> getEmployee(@PathVariable("empId") long empId) {
		return new ResponseEntity(employeePayrollService.getEmployee(empId),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<ResponseDo> deleteEmployee(@PathVariable("empId") long empId) {
		return new ResponseEntity(employeePayrollService.deleteEmployee(empId),HttpStatus.OK);
	}
	
	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDo> updateEmployee(@PathVariable("empId") long empId, @RequestBody EmployeeDO emp) {
		return new ResponseEntity(employeePayrollService.updateEmployee(empId,emp),HttpStatus.OK);
	}
}
