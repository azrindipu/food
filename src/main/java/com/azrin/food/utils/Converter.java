package com.azrin.food.utils;

import com.azrin.food.dto.UserDto;
import com.azrin.food.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    private static final Logger logger = LoggerFactory.getLogger(Converter.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User userDtoToUserEntity(UserDto userDto) throws Exception{
        logger.info("start conversion from userDto to user entity");
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoleName(userDto.getRoleName());
        user.setActive(true);
        logger.info("Conversion finished");
        return user;
    }
    
    public UserDto userEntityToUserDto(User user)throws Exception{
        logger.info("start conversion from user entity to user sto");
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRoleName(user.getRoleName());
        userDto.setMongoId(user.getId());
        logger.info("Conversion finished");
        return userDto;
    }
}
