package in.formValidation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.formValidation.entity.UserEntity;	
import in.formValidation.repository.LoginRepository;
@Service
public class LoginserviceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
    private PasswordEncoder encoder ;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	public boolean registerUser(UserEntity user) {
		user.setPassword(encoder.encode(user.getPassword()));
		if (loginRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("User already exists ! ") ;
		}
		loginRepository.save(user);
		return true;
	}
	
//	@Override
//	public String verify(UserEntity user) throws AuthenticationException {
//		Authentication auth= 
//				authManager.authenticate(
//						new UsernamePasswordAuthenticationToken(
//								user.getEmail(), user.getPassword()));
//		if (auth.isAuthenticated()) {
//			return "Login Successful";
//		}
//		 return "Login Failed";
//	}

	@Override
	public String verify(UserEntity user) {
		// TODO Auto-generated method stub
		Authentication auth=authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		if (auth.isAuthenticated()) {
			return jwtService.generateToken(user.getEmail());
		}
		return "Login Failed";
	}

	

}
