package com.azrin.food.utils;

import com.azrin.food.ExceptionHandler.BadRequest;
import com.azrin.food.model.User;
import com.azrin.food.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validate {
    private static final Logger logger = LoggerFactory.getLogger(Validate.class);

    @Autowired
    private UserRepository userRepository;

    public boolean isRoleValid(String roleName){
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

    public void isIdValid(String string){
        if(string == null || string.equalsIgnoreCase("")){
            throw new BadRequest(ExceptionMessage.INVALID_MONGO_ID);
        }
        else if(string != null){
            string = string.trim();
            if(string == null || string.equalsIgnoreCase("")){
                throw new BadRequest(ExceptionMessage.INVALID_MONGO_ID);
            }
        }
    }

    public boolean isValidUserToDelete(String mongoId) throws Exception{
        User user = userRepository.findById(mongoId);
        if(user == null){
            return false;
        }
        return true;
    }

    public boolean isStringValid(String string){
        if(string == null || string.equalsIgnoreCase("")){
            return false;
        }
        else if(string.trim().equalsIgnoreCase("")){
            return false;
        }
        return true;
    }
}
