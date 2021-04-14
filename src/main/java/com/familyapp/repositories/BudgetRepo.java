package com.familyapp.repositories;

import com.familyapp.models.entities.Budget;
import com.familyapp.models.viewModels.BudgetViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {

    List<Budget> findAllByAddedBy(String username);
    List<Budget> findAllByAddedBy_Family(String familyName);

    List<Budget> findByAddedBy(String username);

}
