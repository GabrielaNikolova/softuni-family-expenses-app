package com.familyapp.repositories;

import com.familyapp.models.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {

    Budget findByAddedBy_Username(String addedBy);

    @Query("SELECT b.budgetAmount FROM Budget AS b WHERE b.addedBy.id=:id")
    Long findBudgetAmountByUser(Long id);

    @Query("SELECT SUM(b.budgetAmount) FROM Budget AS b WHERE b.addedBy.family.id=:id")
    Long findBudgetAmountByFamily(Long id);


}
