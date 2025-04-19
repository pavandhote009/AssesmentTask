package in.formValidation.service;

import org.springframework.stereotype.Service;

import in.formValidation.entity.UserEntity;

@Service
public interface LoginService {
	    boolean registerUser(UserEntity user);
		String verify(UserEntity user);
	  

}
