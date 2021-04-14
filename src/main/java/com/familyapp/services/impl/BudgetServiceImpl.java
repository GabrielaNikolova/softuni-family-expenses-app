package com.familyapp.services.impl;

import com.familyapp.models.entities.Budget;
import com.familyapp.models.entities.User;
import com.familyapp.models.serviceModels.BudgetAddServModel;
import com.familyapp.models.viewModels.BudgetViewModel;
import com.familyapp.repositories.BudgetRepo;
import com.familyapp.services.BudgetService;
import com.familyapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepo budgetRepo;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public BudgetServiceImpl(BudgetRepo budgetRepo, UserService userService, ModelMapper modelMapper) {
        this.budgetRepo = budgetRepo;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public void addBudget(BudgetAddServModel budgetAddServiceModel) {
        Budget newBudget = modelMapper.map(budgetAddServiceModel, Budget.class);

        newBudget.setReceivedOn(LocalDate.now());
        BigDecimal budgetAmount = newBudget.getIncomeAmount().subtract(newBudget.getSavingsAmount());

        newBudget.setBudgetAmount(budgetAmount);

        User addedByUser = userService
                .findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        newBudget.setAddedBy(addedByUser);
        budgetRepo.save(newBudget);

        if (!addedByUser.getPrivateBudget().isEmpty()) {
            addedByUser.getPrivateBudget().add(newBudget);
        } else {
            List<Budget> usersBudgets = new ArrayList<>();
            usersBudgets.add(newBudget);
            addedByUser.setPrivateBudget(usersBudgets);
        }


    }


    @Override
    public  List<BudgetViewModel> findAllByUser() {
        return budgetRepo.findAllByAddedBy(SecurityContextHolder.getContext().getAuthentication().getName())
                .stream().map(b->modelMapper.map(b, BudgetViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BudgetViewModel findByMonth() {
        return null;
    }


}
