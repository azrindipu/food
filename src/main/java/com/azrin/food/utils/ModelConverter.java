package com.azrin.food.utils;

import com.azrin.food.dto.FoodDto;
import com.azrin.food.model.Food;
import org.springframework.stereotype.Component;


@Component
public class ModelConverter {

    public Food convertDtoToEntity(FoodDto foodDto) throws Exception{
        Food food = new Food();
        food.setPoster_id(foodDto.getPosterId());
        food.setTitle(foodDto.getTitle());
        food.setPerson_count(foodDto.getPersonCount());
        food.setPosted_by(foodDto.getPostedBy());
        food.setImage_url(foodDto.getImageLocation());
        food.setCurrent_time(DateUtils.getCurrentDate());
        food.setFood_destroy_time(DateUtils.stringToDate(foodDto.getDestroyTime()));
        food.setPoster_current_lat(foodDto.getPosterCurrentLocationLat());
        food.setPoster_current_longi(foodDto.getPosterCurrentLocationLongi());
        food.setStatus(foodDto.getStatus());
        food.setDescription(foodDto.getDescription());
        return food;
    }

    public FoodDto convertEntityToDto(Food food) throws Exception{
        FoodDto foodDto = new FoodDto();
        foodDto.setMongoId(food.getId());
        foodDto.setPosterId(food.getPoster_id());
        foodDto.setTitle(food.getTitle());
        foodDto.setPersonCount(food.getPerson_count());
        foodDto.setPostedBy(food.getPosted_by());
        foodDto.setImageLocation(food.getImage_url());
        foodDto.setCreationTime(DateUtils.dateToString(food.getCurrent_time()));
        foodDto.setDestroyTime(DateUtils.dateToString(food.getFood_destroy_time()));
        foodDto.setPosterCurrentLocationLat(food.getPoster_current_lat());
        foodDto.setPosterCurrentLocationLongi(food.getPoster_current_longi());
        foodDto.setStatus(food.getStatus());
        foodDto.setDescription(food.getDescription());
        return foodDto;
    }
}
