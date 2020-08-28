package com.azrin.food.service;

import com.azrin.food.ExceptionHandler.NotFound;
import com.azrin.food.model.Food;
import com.azrin.food.repository.FoodRepository;
import com.azrin.food.utils.ExceptionMessage;
import com.azrin.food.utils.FoodStatus;
import com.azrin.food.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private Validate validate;

    @Autowired
    private ImageService imageService;

    @Value("classpath:download.png")
    private Resource resource;

    public boolean createFoodPost(Food food) throws Exception{
        if(!validate.isValidUser(food.getPoster_id())){
            throw new NotFound(ExceptionMessage.USER_NOT_FOUND);
        }
        String imageUrl = imageService.uploadImage(new File(food.getImage_url()));
        food.setImage_url(imageUrl);
        food.setStatus(FoodStatus.FOOD_STATUS_CREATED);
        food = foodRepository.saveFood(food);
        if(food == null){
            return false;
        }
        return true;
    }

    public Food getFoodPostById(String mongoId) throws Exception{
        Food food = foodRepository.getFoodPost(mongoId);
        if(food == null){
            throw new NotFound(ExceptionMessage.FOOD_POST_NOT_FOUND);
        }
        return food;
    }

    public boolean updateFoodPostStatusById(String mongoId, String status) throws Exception{
        Food food = foodRepository.getFoodPost(mongoId);
        if(food == null){
            throw new NotFound(ExceptionMessage.FOOD_POST_NOT_FOUND);
        }
        if(!validate.isValidStatus(status)){
            throw new NotFound(ExceptionMessage.INVALID_STATUS);
        }
        if(!validate.isStatusOrderalid(food.getStatus(), status)){
            throw new NotFound(ExceptionMessage.INVALID_STATUS_ORDER);
        }
        food.setStatus(status);
        food = foodRepository.saveFood(food);
        if(food == null){
            return false;
        }
        return true;
    }




}
