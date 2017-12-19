package info.vdsi.nags.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import info.vdsi.nags.app.beans.UserData;
import info.vdsi.nags.app.model.SaveUserDetails;

@RestController
public class SaveUserDetailsController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/register")
	public ModelAndView displayForm() {
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("userForm");
		
		return modelView;
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public ModelAndView saveEmployeeDetails(UserData user) {
		
		ModelAndView modelView = new ModelAndView();
		SaveUserDetails ed = new SaveUserDetails();
		user = ed.saveUserDetails(jdbcTemplate, user);
		
		modelView.setViewName("index");
		modelView.addObject("user", user);
		
		return modelView;
	}
	
}
