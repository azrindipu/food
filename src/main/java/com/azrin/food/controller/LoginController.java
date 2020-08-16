package com.azrin.food.controller;

import com.azrin.food.ExceptionHandler.BadRequest;
import com.azrin.food.ExceptionHandler.ControllerLevelException;
import com.azrin.food.dto.JwtRequest;
import com.azrin.food.service.CustomUserDetailsService;
import com.azrin.food.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(Constants.BASE_API_URL)
@Api(value = SwaggerValues.LOGIN_CONTROLLER_VALUE)
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService jwtInMemoryUserDetailsService;

	@ApiOperation(value = SwaggerValues.LOGIN_CONTROLLER_LOGIN_POST_VALUE,
			notes = SwaggerValues.LOGIN_CONTROLLER_LOGIN_POST_NOTES,
			position = SwaggerValues.Login_CONTROLLER_Login_POST_POSSITION)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = SwaggerValues.STATUS_OK, response = ResponseEntity.class),
			@ApiResponse(code = 400, message = SwaggerValues.STATUS_BAD_REQUEST, response = String.class),
			@ApiResponse(code = 500, message = SwaggerValues.STATUS_INTERNAL_SERVER_ERROR, response = String.class),
			@ApiResponse(code = 404, message = SwaggerValues.STATUS_NOT_FOUND, response = String.class)
	})

	@PostMapping(value = AllEndPoints.LOGIN_POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<JSONObject> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest,
																BindingResult bindingResult) throws Exception {

		logger.info("login start: userName: "+authenticationRequest.getUsername()+" password: "
				+authenticationRequest.getPassword());
		this.checkBindingResult(bindingResult);
		logger.info("performing authentication");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		logger.info("token: "+token);
		JSONObject responseBody = new JSONObject();
		responseBody.put(Constants.RESPONSE_BODY_DATA, token);
		responseBody.put(Constants.RESPONSE_BODY_STATUS, HttpStatus.OK);
		responseBody.put(Constants.RESPONSE_BODY_ERROR_MESSAGE, Constants.MESSAGE_ERROR);

		logger.info(responseBody.toString());
		return ResponseEntity.ok(responseBody);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			logger.info("Exception: "+e.getMessage());
			throw new BadRequest("Invalid username or password.");
		} catch (BadCredentialsException e) {
			logger.info("Exception: "+e.getMessage());
			throw new BadRequest("Invalid username or password.");
		}catch (Exception e){
			logger.info("Exception: "+e.getMessage());
			throw new BadRequest("Invalid username or password.");
		}
	}

	private void checkBindingResult(BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()){
			List<ObjectError> errorList = bindingResult.getAllErrors();
			List<String> errors = new ArrayList<>();
			for(int i = 0; i < errorList.size(); i++){
				errors.add(errorList.get(i).getDefaultMessage());
			}
			throw new ControllerLevelException(ExceptionMessage.USER_INIT_VALIDATION_EXCEPTION, errors);
		}
	}
}
