package com.azrin.food.dto;


import com.azrin.food.utils.ValidationMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "User DTO")
public class UserDto {

    @JsonProperty("email")
    @NotBlank(message = ValidationMessage.EMAIL_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.EMAIL_CAN_NOT_BE_NULL)
    @Email(message = ValidationMessage.INVALID_EMAIL)
    @ApiModelProperty(required = true, example = "xyz@gmail.com")
    private String email;

    @JsonProperty("password")
    @NotEmpty(message = ValidationMessage.PASSWORD_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.PASSWORD_CAN_NOT_BE_NULL)
    @Min(value = 8, message = ValidationMessage.INVALID_PASSWORD)
    @ApiModelProperty(required = true, example = "12345678")
    private String password;

    @JsonProperty("role_name")
    @NotEmpty(message = ValidationMessage.ROLE_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.ROLE_CAN_NOT_BE_NULL)
    @ApiModelProperty(required = true, example = "user")
    private String roleName;

    @JsonProperty("mongo_id")
    private String mongoId;

}
