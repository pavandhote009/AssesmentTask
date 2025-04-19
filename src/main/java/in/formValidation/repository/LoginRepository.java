package in.formValidation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.formValidation.entity.UserEntity;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {
	
	// Additional query methods can be defined here if needed
	 Optional<UserEntity> findByEmail(String email);
	 
	 UserEntity findByName(String name);	

}
