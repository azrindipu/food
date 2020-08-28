package com.azrin.food.repository;

import com.azrin.food.model.Food;
import com.azrin.food.utils.Constants;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FoodRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Food saveFood(Food food){
        Food savedFood = mongoTemplate.save(food);
        return savedFood;
    }

    public Food getFoodPost(String mongoId){
        Query query=new Query();
        query.addCriteria(Criteria.where(Constants._ID).is(new ObjectId(mongoId)));
        Food user = mongoTemplate.findOne(query, Food.class);
        return user;
    }
}
