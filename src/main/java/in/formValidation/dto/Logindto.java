package in.formValidation.dto;

import java.beans.JavaBean;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@JavaBean
@Data
public class Logindto {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
}
