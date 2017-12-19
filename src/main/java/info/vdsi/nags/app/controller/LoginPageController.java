package info.vdsi.nags.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import info.vdsi.nags.app.beans.UserLoginData;
import info.vdsi.nags.app.model.AddMovieDetails;
import info.vdsi.nags.app.model.GetUserData;

@RestController
public class LoginPageController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/")
	public ModelAndView index(@Valid @ModelAttribute("user") UserLoginData user, BindingResult result, Model model) {
		//model.addAttribute("login", new UserLoginData());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("index");
		
		return modelView;
	}
	@RequestMapping(value= "/login", method=RequestMethod.POST)
	public ModelAndView getCredentials(@Valid @ModelAttribute("user") UserLoginData user, BindingResult result, Model model) {
		
		ModelAndView modelView = new ModelAndView();
		
		if (result.hasErrors()){
			modelView.setViewName("index");
			return modelView;
		}
		GetUserData usrData =  new GetUserData();
		String pwd = user.getPassword();
		UserLoginData data = usrData.getPassword(jdbcTemplate, user.getUserName());
		if(pwd.equals(data.getPassword())) {
			if("Y".equalsIgnoreCase(data.getAdmininfo())) {
				modelView.setViewName("showModifyMovieDetails");
				return modelView;
			}else {
				AddMovieDetails am =new AddMovieDetails();
				modelView.addObject("movies", am.getAllMovies(jdbcTemplate));
				modelView.setViewName("showMovieDetails");
				return modelView;
			}
		}else {
			modelView.setViewName("index");
			return modelView;
		}
		//modelView.addObject("userLoginData", user);
	}
	
}
