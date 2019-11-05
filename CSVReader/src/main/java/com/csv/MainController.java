package com.csv;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
	@Autowired
    private CustomerRepository customerRepository;
	
	
	@RequestMapping(value = "/import", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody String importData(@RequestBody String directory) throws Exception {
	CSVReader cv=new CSVReader();
	return cv.createParser(customerRepository, directory);
	}
	
	@GetMapping("/index")
	public ModelAndView init() {
		  ModelAndView m=new ModelAndView("index");
		  return m;
	}
}
