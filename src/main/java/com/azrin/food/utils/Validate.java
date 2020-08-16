package com.azrin.food.utils;


import com.azrin.food.ExceptionHandler.AlreadyExist;
import com.azrin.food.ExceptionHandler.BadRequest;
import com.azrin.food.ExceptionHandler.NotFound;
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

    public boolean isRoleValid(String roleName) throws Exception{
        logger.info("role name: "+roleName);
        if(roleName.equalsIgnoreCase(RoleType.ROLE_ADMIN) ||
                roleName.equalsIgnoreCase(RoleType.ROLE_USER) ||
                roleName.equalsIgnoreCase(RoleType.ROLE_MANAGER)){
            return true;
        }
        throw new BadRequest(ExceptionMessage.INVALID_ROLE_NAME);
    }

    public void isUserExist(String email){
        logger.info("email: "+email);
        User user = userRepository.findByEmail(email);
        if(user != null){
            throw new AlreadyExist(ExceptionMessage.USER_ALREADY_EXIST);
        }
    }

    public void isIdValid(String string){
        if(string == null || string.equalsIgnoreCase("")){
            throw new BadRequest(ExceptionMessage.INVALID_MONGO_ID);
        }
    }

    public void isValidUserToDelete(String mongoId){
        User user = userRepository.findById(mongoId);
        if(user == null){
            throw new NotFound(ExceptionMessage.USER_NOT_FOUND);
        }
    }
}
