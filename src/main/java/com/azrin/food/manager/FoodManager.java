package com.azrin.food.manager;

import com.azrin.food.dto.FoodDto;
import com.azrin.food.dto.FoodStatusDto;
import com.azrin.food.model.Food;
import com.azrin.food.service.FoodService;
import com.azrin.food.utils.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoodManager {

    @Autowired
    private ModelConverter modelConverter;

    @Autowired
    private FoodService foodService;

    public boolean createFoodPost(FoodDto foodDto) throws Exception{
        Food food = modelConverter.convertDtoToEntity(foodDto);
        boolean result = foodService.createFoodPost(food);
        return result;
    }

    public FoodDto getFoodPostById(String mongoId) throws Exception{
        Food food = foodService.getFoodPostById(mongoId);
        FoodDto foodDto = modelConverter.convertEntityToDto(food);
        return foodDto;
    }

    public boolean updateFoodPostStatusById(FoodStatusDto foodStatusDto) throws Exception{
        boolean result = foodService.updateFoodPostStatusById(foodStatusDto.getMongoId(), foodStatusDto.getStatus());
        return result;
    }
}
