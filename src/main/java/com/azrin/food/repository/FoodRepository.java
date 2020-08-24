package com.azrin.food.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodRepository {

    @Autowired
    private UserRepository userRepository;
}
