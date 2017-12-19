package info.vdsi.nags.app.controller;

import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import info.vdsi.nags.app.beans.UserData;
import info.vdsi.nags.app.model.AjaxResponseBody;
import info.vdsi.nags.app.model.GetUserData;
import info.vdsi.nags.app.model.SearchCriteria;

@RestController
public class CheckUserNameController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/getUser")
	public ResponseEntity checkUserName(@Valid @RequestBody SearchCriteria search, Errors errors) {
		
		AjaxResponseBody result = new AjaxResponseBody();
		GetUserData usr = new GetUserData();
		if ("".equals(search.getUsername())|| null==search.getUsername()) {
            result.setMsg("User Name is Mandatory");
            return ResponseEntity.badRequest().body(result);

        }
		
		String userName = usr.getUserName(jdbcTemplate,search.getUsername());
		
		if(search.getUsername().equals(userName)) {
			result.setMsg("user already exists!!");
			result.setUserName(userName);
			result.setUserExists(true);
		}else {
			result.setUserExists(false);
		}
		return ResponseEntity.ok(result);
	}
	
}
