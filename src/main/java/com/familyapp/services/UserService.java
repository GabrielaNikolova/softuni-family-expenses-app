package com.familyapp.services;

import com.familyapp.models.entities.User;
import com.familyapp.models.serviceModels.UserRegistrationServModel;

public interface UserService {

    boolean userNameExists(String username);

    User findByName(String username);

    User findById(Long id);

    void registerAndLoginUser(UserRegistrationServModel userServiceModel);

    void updateUserExpenses(Long expenseId);
}
