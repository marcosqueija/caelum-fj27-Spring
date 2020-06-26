package br.com.alura.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	 private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
	.withUser("marcos")
	.password(new BCryptPasswordEncoder().encode("123456"))
	.authorities("ROLE_ADMIN")
	.and()
	.withUser("rafael")
	.password(new BCryptPasswordEncoder().encode("123456"))
	.authorities("ROLE_USER")
	.and()
	.passwordEncoder(new BCryptPasswordEncoder());
	}

}
