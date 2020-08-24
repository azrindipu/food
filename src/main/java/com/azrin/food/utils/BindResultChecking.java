package com.azrin.food.utils;

import com.azrin.food.ExceptionHandler.ControllerLevelException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class BindResultChecking {

    public static void checkBindingResult(BindingResult bindingResult) throws Exception{
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
