package com.springboot.thymeleafdemo.controller;

import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> employees = employeeService.findAll();

		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/show-form-for-add")
	public String showFormForAdd(Model model) {
		Employee employee = new Employee();

		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@GetMapping("/show-form-for-update")
	public String showFormForUpdate(@RequestParam("employeeId") int employeeId,
										Model model) {
		Employee employee = employeeService.findById(employeeId);

		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int employeeId) {
		employeeService.deleteById(employeeId);
		return "redirect:/employees/list";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees/list";
	}

}









