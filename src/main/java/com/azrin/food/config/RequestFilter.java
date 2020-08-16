package com.azrin.food.config;

import com.azrin.food.service.CustomUserDetailsService;
import com.azrin.food.utils.Constants;
import com.azrin.food.utils.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	@Autowired
	private TokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain chain) throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader(Constants.AUTHORIZED);
		logger.info("header: "+requestTokenHeader);

		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith(Constants.BEARER)) {
			jwtToken = requestTokenHeader.substring(7);
			logger.info("token: "+jwtToken);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				logger.info("userName: "+username);
			} catch (IllegalArgumentException e) {
				logger.info("Exception: Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				logger.info("Exception: JWT Token has expired");
			}
		} else {
			logger.info("JWT Token does not begin with Bearer String");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}
