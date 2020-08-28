package com.azrin.food.utils;

import com.azrin.food.ExceptionHandler.BadRequest;
import com.azrin.food.model.User;
import com.azrin.food.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Validate {
    private static final Logger logger = LoggerFactory.getLogger(Validate.class);

    @Autowired
    private UserRepository userRepository;

    public boolean isRoleValid(String roleName) throws Exception{
        logger.info("role name: "+roleName);
        if(roleName.equalsIgnoreCase(RoleType.ROLE_ADMIN) ||
                roleName.equalsIgnoreCase(RoleType.ROLE_USER) ||
                roleName.equalsIgnoreCase(RoleType.ROLE_MANAGER)){
            return true;
        }
        return false;
    }

    public boolean isUserExist(String email) throws Exception{
        logger.info("email: "+email);
        User user = userRepository.findByEmail(email);
        if(user != null){
            return true;
        }
        return false;
    }

    public boolean isValidUser(String mongoId) throws Exception{
        User user = userRepository.getUserById(mongoId);
        if(user == null){
            return false;
        }
        return true;
    }

    public boolean isStringValid(String string) throws Exception{
        if(string == null || string.equalsIgnoreCase("")){
            return false;
        }
        else if(string.trim().equalsIgnoreCase("")){
            return false;
        }
        return true;
    }

    public boolean isValidStatus(String status){
        List<String> allStatus = this.getAllStatus();
        if(!allStatus.contains(status)){
            return false;
        }
        return true;
    }

    public boolean isStatusOrderalid(String currentStatus, String givenStatus){
        List<String> allStatus = this.getAllStatus();
        int givenStatusIndex = allStatus.indexOf(givenStatus);
        if(!allStatus.get(givenStatusIndex -1).equalsIgnoreCase(currentStatus)){
            return false;
        }
        return true;
    }

    private List<String> getAllStatus(){
        List<String> status = new ArrayList<>();
        status.add(FoodStatus.FOOD_STATUS_CREATED);
        status.add(FoodStatus.FOOD_STATUS_READY_TO_TAKE);
        status.add(FoodStatus.FOOD_STATUS_ORDERED);
        status.add(FoodStatus.FOOD_STATUS_TAKEN);
        status.add(FoodStatus.FOOD_STATUS_WASTED);
        return status;
    }
}
