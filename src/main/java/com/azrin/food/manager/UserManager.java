package com.azrin.food.manager;

import com.azrin.food.dto.PageInfoDto;
import com.azrin.food.dto.UserDto;
import com.azrin.food.model.User;
import com.azrin.food.service.UserService;
import com.azrin.food.utils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserManager {
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private Converter converter;

    @Autowired
    private UserService userService;

    public UserDto createUser(UserDto userDto) throws Exception{
        logger.info("Called manager to manage request");
        return converter.userEntityToUserDto(userService.createUser(converter.userDtoToUserEntity(userDto)));
    }

    public List<UserDto> getUsers(PageInfoDto pageInfoDto, String pageNumber, String pageSize)throws Exception{
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userService.getUsers(pageInfoDto, pageNumber, pageSize);
        if(users != null){
            for(User user : users){
                userDtos.add(converter.userEntityToUserDto(user));
            }
        }
        return userDtos;
    }

    public boolean deleteUser(String mongoId) throws Exception{
        return userService.deleteUser(mongoId);
    }

    public UserDto getUserById(String mongoId) throws Exception{
        User user = userService.getUserById(mongoId);
        UserDto userDto = converter.userEntityToUserDto(user);
        return userDto;
    }
}
