package com.azrin.food.controller;


import com.azrin.food.ExceptionHandler.*;
import com.azrin.food.dto.PageInfoDto;
import com.azrin.food.dto.UserDto;
import com.azrin.food.manager.UserManager;
import com.azrin.food.utils.AllEndPoints;
import com.azrin.food.utils.Constants;
import com.azrin.food.utils.ExceptionMessage;
import com.azrin.food.utils.SwaggerValues;
import io.swagger.annotations.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public ResponseEntity<JSONObject> createUser(@Valid @RequestBody UserDto userDto,
                                                 BindingResult bindingResult)throws Exception {
        this.checkBindingResult(bindingResult);
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
        responseBody.put(Constants.RESPONSE_BODY_ERROR_MESSAGE, Constants.MESSAGE_ERROR);

        logger.info(responseBody.toString());
        return ResponseEntity.ok(responseBody);
    }

    @ApiOperation(value = SwaggerValues.USER_CONTROLLER_USERS_GET_VALUE,
            notes = SwaggerValues.USER_CONTROLLER_USERS_GET_NOTES,
            position = SwaggerValues.USER_CONTROLLER_USER_GET_POSSITION)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerValues.STATUS_OK, response = ResponseEntity.class),
            @ApiResponse(code = 400, message = SwaggerValues.STATUS_BAD_REQUEST, response = String.class),
            @ApiResponse(code = 500, message = SwaggerValues.STATUS_INTERNAL_SERVER_ERROR, response = String.class),
            @ApiResponse(code = 404, message = SwaggerValues.STATUS_NOT_FOUND, response = String.class)
    })
    @GetMapping(value = AllEndPoints.USERS_GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> getUsers(@ApiParam(name = "page_number", example = "0",required = false, value = "Page Number")
                                               @RequestParam(required = false) int page_number,
                                               @ApiParam(name = "page_size",example = "10",required = false, value = "Page Size")
                                               @RequestParam(required = false) int page_size)throws Exception {

        List<UserDto> result = null;
        PageInfoDto pageInfoDto = new PageInfoDto();
        try {
            logger.info("Calling service");
            result = userManager.getUsers(pageInfoDto, page_number, page_size);
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
        responseBody.put(Constants.RESPONSE_BODY_PAGEINFO, pageInfoDto);
        responseBody.put(Constants.RESPONSE_BODY_STATUS, HttpStatus.OK);
        responseBody.put(Constants.RESPONSE_BODY_ERROR_MESSAGE, Constants.MESSAGE_ERROR);

        logger.info(responseBody.toString());
        return ResponseEntity.ok(responseBody);
    }

    @ApiOperation(value = SwaggerValues.USER_CONTROLLER_USERS_DELETE_VALUE,
            notes = SwaggerValues.USER_CONTROLLER_USERS_DELETE_NOTES,
            position = SwaggerValues.USER_CONTROLLER_USER_DELETE_POSSITION)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerValues.STATUS_OK, response = ResponseEntity.class),
            @ApiResponse(code = 400, message = SwaggerValues.STATUS_BAD_REQUEST, response = String.class),
            @ApiResponse(code = 500, message = SwaggerValues.STATUS_INTERNAL_SERVER_ERROR, response = String.class),
            @ApiResponse(code = 404, message = SwaggerValues.STATUS_NOT_FOUND, response = String.class)
    })
    @DeleteMapping(value = AllEndPoints.USERS_DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> deleteUser(@ApiParam(name = "mongo_id", example = "5f3926b90b2e5754a5061261",required = true, value = "User mongo ID")
                                               @RequestParam(required = true) String mongo_id)throws Exception {

        boolean result = false;
        try {
            logger.info("Calling service");
            result = userManager.deleteUser(mongo_id);
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
        responseBody.put(Constants.RESPONSE_BODY_ERROR_MESSAGE, Constants.MESSAGE_ERROR);

        logger.info(responseBody.toString());
        return ResponseEntity.ok(responseBody);
    }




    private void checkBindingResult(BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            List<ObjectError> errorList = bindingResult.getAllErrors();
            List<String> errors = new ArrayList<>();
            for(int i = 0; i < errorList.size(); i++){
                errors.add(errorList.get(i).getDefaultMessage());
            }
            throw new ExceptionResponseTemplate(new Date().toString(),
                    ExceptionMessage.USER_INIT_VALIDATION_EXCEPTION,
                    errors);
        }
    }
}
