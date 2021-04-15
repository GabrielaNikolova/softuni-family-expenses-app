package com.familyapp.repositories;

import com.familyapp.models.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(amount) FROM family_app_db.expenses WHERE added_from_id=:id GROUP BY YEAR(created_on), MONTH(created_on) ORDER BY YEAR(created_on) DESC, MONTH(created_on) DESC LIMIT 1")
    Long findExpensesByUser(Long id);
}
