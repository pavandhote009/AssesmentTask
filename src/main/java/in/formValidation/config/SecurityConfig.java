package in.formValidation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.formValidation.service.MyUserdetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTFilter jwtFilter;
		
    @Autowired	
    private UserDetailsService userDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
		     .csrf(customizer-> customizer.disable())
		     .authorizeHttpRequests(request-> request.
		    		 requestMatchers("api/register", "api/login","/")
		    		 .permitAll()
		    		 .anyRequest().authenticated())
		     .httpBasic(Customizer.withDefaults())
		     .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		     .addFilterBefore(jwtFilter,  UsernamePasswordAuthenticationFilter.class)
		     .build();
		
	}
	
	@Bean
	PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder(12);
		
	}
	
//	@Bean
//    public UserDetailsService userDetailsService() {
//        // Your UserDetailsService implementation
//        return new InMemoryUserDetailsManager(/* ... */);
//    }
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

}
