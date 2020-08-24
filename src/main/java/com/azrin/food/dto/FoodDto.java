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
@ApiModel(description = "Food DTO")
public class FoodDto {

    @JsonProperty("poster_id")
    @NotBlank(message = ValidationMessage.POSTER_ID_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.POSTER_ID_CAN_NOT_BE_NULL)
    @ApiModelProperty(required = true, example = "Poster ID")
    private String posterId;

    @JsonProperty("title")
    @NotEmpty(message = ValidationMessage.TITLE_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.TITLE_CAN_NOT_BE_NULL)
    @ApiModelProperty(required = true, example = "Bangla Birany")
    private String title;

    @JsonProperty("person_count")
    @Min(value = 1, message = ValidationMessage.INVALID_PERSON_COUNT)
    @ApiModelProperty(required = true, example = "2")
    private int personCount;

    @JsonProperty("image_location")
    @NotEmpty(message = ValidationMessage.IMAGE_LOCATION_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.IMAGE_LOCATION_CAN_NOT_BE_NULL)
    @ApiModelProperty(required = true, example = "image location")
    private String imageLocation;

    @JsonProperty("destroy_time")
    @NotEmpty(message = ValidationMessage.DESTROY_TIME_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.DESTROY_TIME_CAN_NOT_BE_NULL)
    @ApiModelProperty(required = true, example = "destroy time")
    private String destroyTime;

    @JsonProperty("poster_current_location_lat")
    @ApiModelProperty(required = true, example = "1.987655")
    private double posterCurrentLocationLat;

    @JsonProperty("poster_current_location_longi")
    @ApiModelProperty(required = true, example = "1.987655")
    private double posterCurrentLocationLongi;

    @JsonProperty("description")
    @NotEmpty(message = ValidationMessage.DESCRIPTION_LOCATION_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.DESCRIPTION_LOCATION_CAN_NOT_BE_NULL)
    @ApiModelProperty(required = true, example = "Food Description")
    private String description;

    @JsonProperty("posted_by")
    @ApiModelProperty(required = false, hidden = true, example = "Posted By")
    private String postedBy;

    @JsonProperty("creation_time")
    @ApiModelProperty(required = false, hidden = true, example = "Creation Time")
    private String creationTime;

    @JsonProperty("status")
    @ApiModelProperty(required = false, hidden = true, example = "Status, ")
    private String status;


}
