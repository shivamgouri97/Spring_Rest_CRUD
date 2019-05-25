package com.db.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.db.model.Employee;
@RestController
public class EmployeeController {
	@RequestMapping(method= RequestMethod.POST)
	public String saveEmployee(Model model , Employee employee) {
		System.out.println("eno"+employee.getEid()());
		System.out.println("ename"+employee.getEname());
		System.out.println("esalary"+employee.getEsalary());
		model.addAttribute(employee);
		return "display";
	}

	@RequestMapping(value = "/employees",method=RequestMethod.GET,produces=MediaType.APPLICATION_XML_VALUE)
	public ArrayList<Employee> getEmployees(){
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		Employee e3 = new Employee();
		e1.setEname("shivam");
		e2.setEname("sanjay");
		e3.setEname("kiran");
		ArrayList<Employee> list = new ArrayList<>();
		list.add(e1);
		list.add(e2);
		list.add(e3);
		return list;
	}
	
	//@ResponseBody
	@RequestMapping(value = "/employees/{name}",method=RequestMethod.GET)
	public Employee getEmployee(@PathVariable("name") String name){
		Employee e1 = new Employee();
		e1.setEname(name);
		e1.setEno(1212);
		
		return e1;
	}
	
	@RequestMapping(value = "/update/{name}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateEmployees(@PathVariable("name") String name, @RequestBody Employee employee){
		System.out.println(employee.getEname());
		System.out.println(employee.getEno());
		System.out.println(employee.getEsalary());
		HttpHeaders http = new HttpHeaders();
		http.add("Mykey", "MyVal");
		return new ResponseEntity<Boolean>(true,http, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> postEmployees(@RequestBody Employee employee){
		System.out.println(employee.getEname());
		System.out.println(employee.getEno());
		System.out.println(employee.getEsalary());
		HttpHeaders http = new HttpHeaders();
		http.add("Location" ,ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
				.buildAndExpand(employee.getEname()).toUri().toString());
		return new ResponseEntity<Boolean>(true,http, HttpStatus.OK);
	}
	
}
