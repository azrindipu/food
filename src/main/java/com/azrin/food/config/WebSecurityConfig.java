package com.azrin.food.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private RequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//test
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/**" ).permitAll().
				anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	//prod
	/*@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable()
				.authorizeRequests().antMatchers(
				        //for application
				"/api/quixx/v1/initUser/create",
				"/api/quixx/v1/login",
				"/api/quixx/v1/reports/**",
				"/api/quixx/v1/forget/password/**",

				//custom client
				"/api/quixx/v1/express/bangla/merchant/**",
				"/api/quixx/v1/dhaka/bd/merchant/**",
				"/api/quixx/v1/approved/test/**",


				//for android app
				"/api/quixx/v1/deliveryMan/auth/**",
				"/api/quixx/v1/deliveryMan/verify/authCode/**",
				"/api/quixx/v1/deliveryMan/isApproved/**",
				"/api/quixx/v1/deliveryMan/getId/**",
				"/api/quixx/v1/deliveryMan/updateDeliveryManLatLong/**",
				"/api/quixx/v1/deliveryMan/getDeliveryManById/**",
				"/api/quixx/v1/delivery/deliveries/**",
				"/api/quixx/v1/delivery/history/**",
				"/api/quixx/v1/delivery/statusForDeliveryMan/**",
				"/api/quixx/v1/delivery/status/**",
				"/api/quixx/v1/delivery/getStatusByDeliveryId/**").permitAll().
				anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}*/
}
