package com.azrin.food.controller;

import com.azrin.food.ExceptionHandler.AlreadyExist;
import com.azrin.food.ExceptionHandler.BadRequest;
import com.azrin.food.ExceptionHandler.InternalServerError;
import com.azrin.food.ExceptionHandler.NotFound;
import com.azrin.food.dto.FoodDto;
import com.azrin.food.dto.FoodStatusDto;
import com.azrin.food.manager.FoodManager;
import com.azrin.food.utils.AllEndPoints;
import com.azrin.food.utils.BindResultChecking;
import com.azrin.food.utils.Constants;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.BASE_API_URL)
@Api(value = SwaggerValues.FOOD_CONTROLLER_VALUE)
public class FoodController {
    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    @Autowired
    private FoodManager foodManager;

    @ApiOperation(value = SwaggerValues.FOOD_CONTROLLER_POST_VALUE,
            notes = SwaggerValues.FOOD_CONTROLLER_POST_NOTES,
            position = SwaggerValues.FOOD_CONTROLLER_POST_POSSITION)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerValues.STATUS_OK, response = ResponseEntity.class),
            @ApiResponse(code = 400, message = SwaggerValues.STATUS_BAD_REQUEST, response = String.class),
            @ApiResponse(code = 500, message = SwaggerValues.STATUS_INTERNAL_SERVER_ERROR, response = String.class),
            @ApiResponse(code = 404, message = SwaggerValues.STATUS_NOT_FOUND, response = String.class)
    })
    @PostMapping(value = AllEndPoints.FOOD_POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> createFoodPost(@Valid @RequestBody final FoodDto foodDto,
                                                 BindingResult bindingResult)throws Exception {
        BindResultChecking.checkBindingResult(bindingResult);
        boolean result = false;
        try {
            logger.info("Calling service");
            result = foodManager.createFoodPost(foodDto);
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

    @ApiOperation(value = SwaggerValues.FOOD_CONTROLLER_GET_BY_ID_VALUE,
            notes = SwaggerValues.FOOD_CONTROLLER_GET_BY_ID_NOTES,
            position = SwaggerValues.FOOD_CONTROLLER_GET_BY_ID_POSSITION)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerValues.STATUS_OK, response = ResponseEntity.class),
            @ApiResponse(code = 400, message = SwaggerValues.STATUS_BAD_REQUEST, response = String.class),
            @ApiResponse(code = 500, message = SwaggerValues.STATUS_INTERNAL_SERVER_ERROR, response = String.class),
            @ApiResponse(code = 404, message = SwaggerValues.STATUS_NOT_FOUND, response = String.class)
    })
    @PutMapping(value = AllEndPoints.FOOD_GET_BY_ID,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> updateFoodPostStatusById(@ApiParam(name = "mongoId", example = "66241",required = true, value = "Food Mongo ID")
                                                                   @RequestParam(required = true) String mongoId)throws Exception {
        FoodDto result = null;
        try {
            logger.info("Calling service");
            result = foodManager.getFoodPostById(mongoId);
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

    @ApiOperation(value = SwaggerValues.FOOD_CONTROLLER_PUT_STATUS_VALUE,
            notes = SwaggerValues.FOOD_CONTROLLER_PUT_STATUS_NOTES,
            position = SwaggerValues.FOOD_CONTROLLER_PUT_STATUS_POSSITION)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerValues.STATUS_OK, response = ResponseEntity.class),
            @ApiResponse(code = 400, message = SwaggerValues.STATUS_BAD_REQUEST, response = String.class),
            @ApiResponse(code = 500, message = SwaggerValues.STATUS_INTERNAL_SERVER_ERROR, response = String.class),
            @ApiResponse(code = 404, message = SwaggerValues.STATUS_NOT_FOUND, response = String.class)
    })
    @GetMapping(value = AllEndPoints.FOOD_PUT_STATUS,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> getFoodPostById(@Valid @RequestBody final FoodStatusDto foodStatusDto,
                                                      BindingResult bindingResult)throws Exception {
        BindResultChecking.checkBindingResult(bindingResult);
        boolean result = false;
        try {
            logger.info("Calling service");
            result = foodManager.updateFoodPostStatusById(foodStatusDto);
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
}
