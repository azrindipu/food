package com.azrin.food.dto;

import com.azrin.food.utils.ValidationMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Food status DTO")
public class FoodStatusDto {

    @JsonProperty("mongo_id")
    @NotBlank(message = ValidationMessage.POSTER_ID_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.POSTER_ID_CAN_NOT_BE_NULL)
    @ApiModelProperty(required = true, example = "ID")
    private String mongoId;

    @JsonProperty("status")
    @NotBlank(message = ValidationMessage.STATUS_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.STATUS_CAN_NOT_BE_NULL)
    @ApiModelProperty(required = true, example = "Status")
    private String status;
}
