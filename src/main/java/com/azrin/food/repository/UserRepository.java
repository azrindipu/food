package com.azrin.food.repository;


import com.azrin.food.model.User;
import com.azrin.food.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User findByEmail(String email){
        Query query=new Query();
        query.addCriteria(Criteria.where(Constants.EMAIL).is(email));
        return mongoTemplate.findOne(query, User.class);
    }

    public User saveUser(User user) throws Exception{
        User savedUser = mongoTemplate.save(user);
        return savedUser;
    }
}
