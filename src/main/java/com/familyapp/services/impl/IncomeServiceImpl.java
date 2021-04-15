package com.familyapp.services.impl;

import com.familyapp.models.entities.Income;
import com.familyapp.models.entities.User;
import com.familyapp.models.serviceModels.IncomeAddServModel;
import com.familyapp.repositories.IncomeRepo;
import com.familyapp.services.BudgetService;
import com.familyapp.services.IncomeService;
import com.familyapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepo incomeRepo;
    private final UserService userService;
    private final BudgetService budgetService;
    private final ModelMapper modelMapper;

    public IncomeServiceImpl(IncomeRepo incomeRepo, UserService userService, BudgetService budgetService, ModelMapper modelMapper) {
        this.incomeRepo = incomeRepo;
        this.userService = userService;
        this.budgetService = budgetService;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public void addIncome(IncomeAddServModel incomeAddServiceModel) {
        Income newIncome = modelMapper.map(incomeAddServiceModel, Income.class);
        newIncome.setReceivedOn(LocalDate.now());

        BigDecimal budgetAmount = newIncome.getIncomeAmount().subtract(newIncome.getSavingsAmount());
        budgetService.addBudget(budgetAmount);


        User user = userService
                .findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        newIncome.setAddedBy(user);
        incomeRepo.save(newIncome);

        if (user.getPrivateIncomes() == null) {
            List<Income> incomes = new ArrayList<>();
            incomes.add(newIncome);
            user.setPrivateIncomes(incomes);
        } else {
            user.getPrivateIncomes().add(newIncome);
        }

    }

    @Override
    public BigDecimal getMonthlyIncomeByUser() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Long incomeAmount = incomeRepo.findIncomeAmountByUser(user.getId());

        if (incomeAmount == null) {
            return BigDecimal.valueOf(0);
        }

        return BigDecimal.valueOf(incomeAmount);
    }

    @Override
    public BigDecimal getSavingsByUser() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Long savingsAmount = incomeRepo.findSavingsAmountByUser(user.getId());

        if (savingsAmount == null) {
            return BigDecimal.valueOf(0);
        }

        return BigDecimal.valueOf(savingsAmount);
    }
}
