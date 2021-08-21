package com.miracle.UserActivity;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.miracle.UserActivity.security.JWTAuthenticationFilter;
import com.miracle.UserActivity.security.JWTTokenHelper;
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
	private JWTTokenHelper jWTTokenHelper;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	http.httpBasic().disable();
//        http.authorizeRequests()
//                .antMatchers("/findAllUser").hasAuthority("Managerial_Role")
////                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/").permitAll()
//                .and().formLogin().loginProcessingUrl("/login");
//    }
    
   @Bean
    public PasswordEncoder getPasswordEncoder() {
      return NoOpPasswordEncoder.getInstance();
    }
    
//    @Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	public AuthenticationManager getAuthenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and()
				.authorizeRequests((request) -> request.antMatchers("/RegisterUser", "/auth/login").permitAll()
						.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
				.addFilterBefore(new JWTAuthenticationFilter(userDetailsService, jWTTokenHelper),
						UsernamePasswordAuthenticationFilter.class);

		http.csrf().disable().cors().and().headers().frameOptions().disable();

	}
}