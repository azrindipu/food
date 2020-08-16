package com.azrin.food.dto;


import com.azrin.food.utils.ValidationMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class JwtRequest {

	@JsonProperty("user_name")
	@NotEmpty(message = ValidationMessage.USER_NAME_CAN_NOT_BE_EMPTY)
	@NotNull(message = ValidationMessage.USER_NAME_CAN_NOT_BE_NULL)
	@Email(message = ValidationMessage.INVALID_EMAIL)
	private String username;

	@JsonProperty("password")
	@NotEmpty(message = ValidationMessage.PASSWORD_NAME_CAN_NOT_BE_EMPTY)
	@NotNull(message = ValidationMessage.PASSWORD_NAME_CAN_NOT_BE_NULL)
	private String password;
}