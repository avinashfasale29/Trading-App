package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Trading;
import net.javaguides.springboot.service.TradingService;

@Controller
public class TradingController {
	
	@Autowired
	private TradingService tradingService;
	
	// display list of Trading
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "orderid", "asc", model);		
	}
	
	@GetMapping("/showNewEmployeeForm1")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Trading employee = new Trading();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Trading employee) {
		// save trading to database
		tradingService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{orderid}")
	public String showFormForUpdate(@PathVariable ( value = "orderid") long orderid, Model model) {
		
		// get employee from the service
		Trading employee = tradingService.getEmployeeById(orderid);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{orderid}")
	public String deleteEmployee(@PathVariable (value = "orderid") long orderid) {
		
		// call delete employee method 
		this.tradingService.deleteEmployeeById(orderid);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Trading> page = tradingService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Trading> listTrading = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		//Object listTrading = null;
		model.addAttribute("listTrading", listTrading);
		return "index";
	}
	

}
