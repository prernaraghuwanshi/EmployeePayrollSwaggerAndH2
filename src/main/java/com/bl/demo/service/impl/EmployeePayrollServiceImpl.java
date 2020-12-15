package com.bl.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.demo.model.EmployeeEntity;
import com.bl.demo.exception.BadRequestException;
import com.bl.demo.exception.NotFoundException;
import com.bl.demo.repository.EmployeeRepository;
import com.bl.demo.service.IEmployeePayrollService;
import com.bl.demo.model.EmployeeDO;
import com.bl.demo.model.ResponseDo;



@Service
public class EmployeePayrollServiceImpl implements IEmployeePayrollService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public ResponseDo addEmployee(EmployeeDO empReqDo) {

		if (empReqDo == null) {
			throw new BadRequestException("Name is not Proper");
		}
		EmployeeEntity empEntity = new EmployeeEntity();
		empEntity.setName(empReqDo.getName());
		empEntity.setDepartment(empReqDo.getDepartment());
		empEntity.setSalary(empReqDo.getSalary());
		empEntity.setGender(empReqDo.getGender());
		empEntity.setImagePath(empReqDo.getImagePath());
		empEntity.setStartDate(new Date());
		empEntity.setNotes(empReqDo.getNotes());
		empEntity = employeeRepository.save(empEntity);
		if (empEntity != null) {
			return new ResponseDo("Successfully data inserted");
		} else {
			return new ResponseDo("Failed to insert the Data");
		}

	}
	
	@Override
	public List<EmployeeDO> getEmployeeList() {
		List<EmployeeEntity> empList = employeeRepository.findAll();
		if(empList == null || empList.isEmpty()) {
			throw new NotFoundException("No Data Found of any Employee");
		}
		return empList.stream().map(employee -> {
			EmployeeDO emp = new EmployeeDO();
			emp.setName(employee.getName());
			emp.setDepartment(employee.getDepartment());
			emp.setGender(employee.getGender());
			emp.setImagePath(employee.getImagePath());
			emp.setNotes(employee.getNotes());
			emp.setSalary(employee.getSalary());
			emp.setStartDate(employee.getStartDate().toString());
			return emp;
		}).collect(Collectors.toList());
	}
	
	@Override
	public ResponseDo getEmployee(long empId) {
		EmployeeEntity empEntity = new EmployeeEntity();
		empEntity = employeeRepository.findById(empId).get();
		if(empEntity!= null) {
			return new ResponseDo(empEntity.toString());
		} else {
			return new ResponseDo("Failure data retrival");
		}
	}
	
	@Override
	public ResponseDo deleteEmployee(long empId) {
		employeeRepository.deleteById(empId);
		return new ResponseDo("Deleted");
	}
	
	@Override
	public ResponseDo updateEmployee(long empId, EmployeeDO empReqDo) {
		EmployeeEntity empEntity = new EmployeeEntity();
		empEntity = employeeRepository.findById(empId).get();
		empEntity.setName(empReqDo.getName());
		empEntity.setDepartment(empReqDo.getDepartment());
		empEntity.setSalary(empReqDo.getSalary());
		empEntity.setGender(empReqDo.getGender());
		empEntity.setImagePath(empReqDo.getImagePath());
		empEntity.setStartDate(new Date());
		empEntity.setNotes(empReqDo.getNotes());
		empEntity = employeeRepository.save(empEntity);
		System.out.println(empEntity);
		System.out.println(empReqDo);
		if(empEntity!= null) {
			return new ResponseDo("Success data updation");
		} else {
			return new ResponseDo("Failure data updation");
		}
		
	}
}
