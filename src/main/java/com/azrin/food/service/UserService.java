package com.azrin.food.service;

import com.azrin.food.ExceptionHandler.AlreadyExist;
import com.azrin.food.ExceptionHandler.BadRequest;
import com.azrin.food.ExceptionHandler.InternalServerError;
import com.azrin.food.ExceptionHandler.NotFound;
import com.azrin.food.dto.PageInfoDto;
import com.azrin.food.dto.UserDto;
import com.azrin.food.model.User;
import com.azrin.food.repository.UserRepository;
import com.azrin.food.utils.Constants;
import com.azrin.food.utils.ExceptionMessage;
import com.azrin.food.utils.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${default.page.number}")
    private String defaultPageNumber;

    @Value("${default.page.size}")
    private String defaultPageSize;

    @Value("${default.page.size.max}")
    private String pageSizeMax;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Validate validate;

    public User createUser(User user) throws Exception{
        logger.info("Called service and start validation.");
        if(!validate.isRoleValid(user.getRoleName())){
            throw new BadRequest(ExceptionMessage.INVALID_ROLE_NAME);
        }
        if(validate.isUserExist(user.getEmail())) {
            throw new AlreadyExist(ExceptionMessage.USER_ALREADY_EXIST);
        }
        logger.info("Validation finished and going to DAO layer.");
        return userRepository.saveUser(user);
    }

    public User getUserById(String mongoId) throws Exception{
        if(!validate.isStringValid(mongoId)){
            throw new BadRequest(ExceptionMessage.INVALID_MONGO_ID);
        }
        User user = userRepository.getUserById(mongoId);
        if(user == null){
            throw new NotFound(ExceptionMessage.USER_NOT_FOUND);
        }
        return user;
    }

    public List<User> getUsers(PageInfoDto pageInfoDto, String pageNumber, String pageSize) throws Exception{

        int pageN = Integer.parseInt(defaultPageNumber);
        int pageS = Integer.parseInt(defaultPageSize);
        if(pageNumber != null && !pageNumber.equalsIgnoreCase("") && !pageNumber.trim().equalsIgnoreCase("")){
            pageN = Integer.parseInt(pageNumber);
        }
        if(pageSize != null && !pageSize.equalsIgnoreCase("") && !pageSize.trim().equalsIgnoreCase("")){
            pageS = Integer.parseInt(pageSize);
        }
        if(pageS > Integer.parseInt(pageSizeMax)){
            throw new BadRequest(ExceptionMessage.MAX_PAGE_SIZE+pageSizeMax);
        }
        Page pagableData = null;
        List<User> users = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageN, pageS, Sort.Direction.ASC, Constants.EMAIL);
        pagableData = userRepository.getUsers(pageable);
        if(pagableData != null){
            users.addAll(pagableData.getContent());
            pageInfoDto.setFirst(pagableData.isFirst());
            pageInfoDto.setTotalPages(pagableData.getTotalPages());
            pageInfoDto.setTotalElements(pagableData.getTotalElements());
            pageInfoDto.setFirst(pagableData.isFirst());
            pageInfoDto.setNumberOfElements(pagableData.getNumberOfElements());
            pageInfoDto.setSize(pagableData.getSize());
            pageInfoDto.setNumber(pagableData.getNumber());
        }
        return users;
    }

    public boolean deleteUser(String mongoId) throws Exception{
        if(!validate.isStringValid(mongoId)){
            throw new BadRequest(ExceptionMessage.INVALID_MONGO_ID);
        }
        if(!validate.isValidUserToDelete(mongoId)){
            throw new NotFound(ExceptionMessage.USER_NOT_FOUND);
        }
        boolean result = false;
        try {
            result = userRepository.deleteUser(mongoId);
        }catch (Exception e){
            throw new InternalServerError(ExceptionMessage.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

}
