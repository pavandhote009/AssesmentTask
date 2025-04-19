package in.formValidation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.formValidation.dto.Logindto;
import in.formValidation.entity.UserEntity;
import in.formValidation.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	  @GetMapping("/")
	    public String home() {
	        return "index.html"; // Default React page
	    }
	

	@PostMapping("/register")
	UserEntity register(@RequestBody UserEntity user) {
		loginService.registerUser(user);
		return user;
	}
	
	@PostMapping("/login")	
	public String login(@RequestBody UserEntity user) {
		System.out.println(user);
        return loginService.verify(user);
    
	}
    
}
