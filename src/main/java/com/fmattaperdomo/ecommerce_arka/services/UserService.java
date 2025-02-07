package com.fmattaperdomo.ecommerce_arka.services;

import com.fmattaperdomo.ecommerce_arka.dtos.UserDto;
import com.fmattaperdomo.ecommerce_arka.dtos.UserResponse;

public interface UserService {
    UserDto addUser(UserDto user);

    UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    UserResponse searchUserByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    UserDto updateUser(Long userId, UserDto user);

    UserDto deleteUser(Long userId);

}
