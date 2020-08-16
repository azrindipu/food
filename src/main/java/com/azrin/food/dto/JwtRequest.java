package com.azrin.food.dto;


import com.azrin.food.utils.ValidationMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "lohin DTO")
public class JwtRequest {

	@JsonProperty("user_name")
	@NotEmpty(message = ValidationMessage.USER_NAME_CAN_NOT_BE_EMPTY)
	@NotNull(message = ValidationMessage.USER_NAME_CAN_NOT_BE_NULL)
	@Email(message = ValidationMessage.INVALID_EMAIL)
	@ApiModelProperty(required = true, example = "xyz@gmail.com")
	private String username;

	@JsonProperty("password")
	@NotEmpty(message = ValidationMessage.PASSWORD_NAME_CAN_NOT_BE_EMPTY)
	@NotNull(message = ValidationMessage.PASSWORD_NAME_CAN_NOT_BE_NULL)
	@Min(value = 8, message = ValidationMessage.INVALID_PASSWORD)
	@ApiModelProperty(required = true, example = "12345678")
	private String password;
}