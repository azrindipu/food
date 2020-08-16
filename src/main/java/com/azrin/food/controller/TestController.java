package com.azrin.food.controller;

import com.azrin.food.utils.Constants;
import com.azrin.food.utils.SwaggerValues;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(Constants.BASE_API_URL)
@Api(value = SwaggerValues.TEST_CONTROLLER_VALUE)
public class TestController {

    @ApiOperation(value = SwaggerValues.TEST_CONTROLLER_TEST_VALUE,
            notes = SwaggerValues.TEST_CONTROLLER_TEST_NOTES,
            position = SwaggerValues.TEST_CONTROLLER_TEST_POSSITION)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerValues.STATUS_OK, response = ResponseEntity.class),
            @ApiResponse(code = 400, message = SwaggerValues.STATUS_BAD_REQUEST, response = String.class),
            @ApiResponse(code = 500, message = SwaggerValues.STATUS_INTERNAL_SERVER_ERROR, response = String.class),
            @ApiResponse(code = 404, message = SwaggerValues.STATUS_NOT_FOUND, response = String.class)
    })
    @RequestMapping(value = {"test"},
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> test(){

        String result = "Hello";
        JSONObject responseBody = new JSONObject();
        responseBody.put(Constants.RESPONSE_BODY_DATA, result);
        responseBody.put(Constants.RESPONSE_BODY_STATUS, HttpStatus.OK);
        responseBody.put(Constants.RESPONSE_BODY_MESSAGE, Constants.MESSAGE_SUCCESSFUL);

        return ResponseEntity.ok(responseBody);
    }
}
