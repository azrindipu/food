package com.azrin.food.repository;


import com.azrin.food.model.User;
import com.azrin.food.utils.Constants;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User findByEmail(String email){
        Query query=new Query();
        query.addCriteria(Criteria.where(Constants.EMAIL).is(email));
        return mongoTemplate.findOne(query, User.class);
    }

    public User saveUser(User user) {
        User savedUser = mongoTemplate.save(user);
        return savedUser;
    }

    public Page<User> getUsers(Pageable pageable){
        Criteria criteria = new Criteria();
        Query query=new Query(criteria).with(pageable);
        List<User> returnMails = mongoTemplate.find(query, User.class);
        Page<User> page = PageableExecutionUtils.getPage(
                returnMails,
                pageable,
                () -> mongoTemplate.count(query, User.class));
        return page;
    }

    public boolean deleteUser(String mongoId){
        Query query=new Query();
        query.addCriteria(Criteria.where(Constants._ID).is(new ObjectId(mongoId)));
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        if(deleteResult != null){
            return true;
        }
        return false;
    }

    public User getUserById(String mongoId){
        Query query=new Query();
        query.addCriteria(Criteria.where(Constants._ID).is(new ObjectId(mongoId)));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }
}
