package com.azrin.food.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionResponseTemplate extends RuntimeException{

    @JsonProperty("time")
    private String time;

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("error_details")
    private List<String> errorDetails;

    public ExceptionResponseTemplate(String time, String errorMessage, List<String> errorDetails) {
        this.time = time;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }
}
