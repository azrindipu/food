package com.azrin.food.controller;


import com.azrin.food.ExceptionHandler.AlreadyExist;
import com.azrin.food.ExceptionHandler.BadRequest;
import com.azrin.food.ExceptionHandler.InternalServerError;
import com.azrin.food.ExceptionHandler.NotFound;
import com.azrin.food.dto.UserDto;
import com.azrin.food.manager.UserManager;
import com.azrin.food.utils.AllEndPoints;
import com.azrin.food.utils.Constants;
import com.azrin.food.utils.SwaggerValues;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.BASE_API_URL)
@Api(value = SwaggerValues.USER_CONTROLLER_VALUE)
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserManager userManager;

    @ApiOperation(value = SwaggerValues.USER_CONTROLLER_USERS_POST_VALUE,
            notes = SwaggerValues.USER_CONTROLLER_USERS_POST_NOTES,
            position = SwaggerValues.USER_CONTROLLER_USER_POST_POSSITION)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerValues.STATUS_OK, response = ResponseEntity.class),
            @ApiResponse(code = 400, message = SwaggerValues.STATUS_BAD_REQUEST, response = String.class),
            @ApiResponse(code = 500, message = SwaggerValues.STATUS_INTERNAL_SERVER_ERROR, response = String.class),
            @ApiResponse(code = 404, message = SwaggerValues.STATUS_NOT_FOUND, response = String.class)
    })

    @PostMapping(value = AllEndPoints.USERS_POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<JSONObject> createUser(@Valid @RequestBody UserDto userDto)throws Exception {
        UserDto result = null;
        try {
            logger.info("Calling service");
            result = userManager.createUser(userDto);
        }catch (AlreadyExist e){
            throw new AlreadyExist(e.getMessage());
        }catch (NotFound e){
            throw new NotFound(e.getMessage());
        }catch (BadRequest e){
            throw new BadRequest(e.getMessage());
        }catch (InternalServerError e){
            throw new InternalServerError(e.getMessage());
        }catch (Exception e){
            throw new InternalServerError(e.getMessage());
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put(Constants.RESPONSE_BODY_DATA, result);
        responseBody.put(Constants.RESPONSE_BODY_STATUS, HttpStatus.OK);
        responseBody.put(Constants.RESPONSE_BODY_MESSAGE, Constants.MESSAGE_SUCCESSFUL);

        logger.info(responseBody.toString());
        return ResponseEntity.ok(responseBody);
    }
}
