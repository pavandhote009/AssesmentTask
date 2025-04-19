package in.formValidation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.formValidation.entity.UserEntity;
import in.formValidation.entity.UserPrincipal;
import in.formValidation.repository.LoginRepository;

@Service
public class MyUserdetailService implements UserDetailsService {

	@Autowired
	LoginRepository loginRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user=loginRepository.findByEmail(email).orElse(null);
//		System.out.println(user.toString());
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		// TODO Auto-generated method stub
		return new UserPrincipal(user);
	}

}
