package com.familyapp.services.impl;

import com.familyapp.models.entities.Expense;
import com.familyapp.models.entities.Family;
import com.familyapp.models.entities.Role;
import com.familyapp.models.entities.User;
import com.familyapp.models.enumModels.RoleEnums;
import com.familyapp.models.serviceModels.UserRegistrationServModel;
import com.familyapp.repositories.UserRepo;
import com.familyapp.services.FamilyService;
import com.familyapp.services.RoleService;
import com.familyapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsAppService userDetailsAppService;
    private final FamilyService familyService;


    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, RoleService roleService, PasswordEncoder passwordEncoder, UserDetailsAppService userDetailsAppService, FamilyService familyService) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsAppService = userDetailsAppService;
        this.familyService = familyService;
    }


    @Override
    public boolean userNameExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public User findByName(String username) {
        return userRepo.findByUsername(username).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public void registerAndLoginUser(UserRegistrationServModel userServiceModel) {
        User newUser = modelMapper.map(userServiceModel, User.class);
        newUser.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        Family family = familyService.findByEmail(userServiceModel.getFamilyEmail());

        List<Role> userRoles = new ArrayList<>();
        newUser.setRoles(userRoles);

        if (family.getFamilyMembers().isEmpty()) {
            Role adminRole = roleService.
                    findByRole(RoleEnums.ADMIN);
            newUser.getRoles().add(adminRole);

            List<User> familyMembers = new ArrayList<>();
            family.setFamilyMembers(familyMembers);
        }
        Role userRole = roleService.
                findByRole(RoleEnums.USER);
        newUser.getRoles().add(userRole);

        family.getFamilyMembers().add(newUser);

        newUser.setFamily(family);


        newUser = userRepo.save(newUser);

        UserDetails principal = userDetailsAppService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    @Transactional
    public void updateUserExpenses(Long expenseId) {
        User user = findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Expense> expenses = user.getExpenses();
        List<Expense> newList = expenses;

        newList.removeIf(e -> e.getId().equals(expenseId));
        user.setExpenses(newList);

    }

}
