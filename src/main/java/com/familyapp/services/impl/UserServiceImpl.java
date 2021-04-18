package com.familyapp.services.impl;

import com.familyapp.models.entities.*;
import com.familyapp.models.enumModels.RoleEnums;
import com.familyapp.models.serviceModels.UserRegistrationServModel;
import com.familyapp.models.viewModels.UserViewModel;
import com.familyapp.repositories.UserRepo;
import com.familyapp.services.FamilyService;
import com.familyapp.services.RoleService;
import com.familyapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        if (family.getFamilyName().equals("admin") && newUser.getUsername().equals("admin")) {
            Role adminRole = roleService.
                    findByRole(RoleEnums.ADMIN);
            newUser.getRoles().add(adminRole);

            List<User> familyMembers = new ArrayList<>();
            family.setFamilyMembers(familyMembers);

        }

//        if (family.getFamilyMembers().isEmpty()) {
//            Role adminRole = roleService.
//                    findByRole(RoleEnums.ADMIN);
//            newUser.getRoles().add(adminRole);
//
//            List<User> familyMembers = new ArrayList<>();
//            family.setFamilyMembers(familyMembers);
//        }
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

        expenses.removeIf(e -> e.getId().equals(expenseId));
        user.setExpenses(expenses);

    }

    @Override
    public void updateUserEvents(Long eventId) {
        User user = findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Event> newList = user.getEvents();

        newList.removeIf(e -> e.getId().equals(eventId));
        user.setEvents(newList);

    }


    @Override
    public List<String> getAllUsernames() {
        List<User> users = userRepo.findAll();
        List<String> usernames = new ArrayList<>();
        users.forEach(u -> usernames.add(u.getUsername()));
        return usernames;
    }

    @Override
    public void changeUserRole(String username, RoleEnums roleEnum) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " was not found!"));
        ;
        Role role = roleService.findByRole(roleEnum);

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepo.save(user);
        }
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        List<User> users = userRepo.findAll();
        return getUserViewModels(users);
    }

    @Override
    public List<UserViewModel> getAllFamilyMembers() {
        User user = findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<User> users = userRepo.findAllByFamily_Id(user.getFamily().getId());

        return getUserViewModels(users);
    }

    private List<UserViewModel> getUserViewModels(List<User> users) {
        return users.stream()
                .map(u -> {
                    UserViewModel userView = modelMapper.map(u, UserViewModel.class);
                    List<String> userRoles = u.getRoles().stream().map(r -> String.valueOf(r.getName())).collect(Collectors.toList());
                    userView.setRoles(userRoles);
                    return userView;
                }).collect(Collectors.toList());
    }

}
