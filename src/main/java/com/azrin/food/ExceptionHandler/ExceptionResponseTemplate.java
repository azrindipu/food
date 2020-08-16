package com.azrin.food.ExceptionHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ExceptionResponseTemplate {

    private String time;
    private String errorMessage;
    private List<String> errorDetails;

    public ExceptionResponseTemplate(String time, String errorMessage, List<String> errorDetails) {
        this.time = time;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }
}
