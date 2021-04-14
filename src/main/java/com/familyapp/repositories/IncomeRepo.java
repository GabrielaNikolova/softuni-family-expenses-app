package com.familyapp.repositories;

import com.familyapp.models.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(income_amount) FROM family_app_db.incomes WHERE added_by_id=:id GROUP BY YEAR(received_on), MONTH(received_on) ORDER BY YEAR(received_on) DESC, MONTH(received_on) DESC LIMIT 1")
    Long findIncomeAmountByUser(@Param("id") Long id);

    @Query("SELECT SUM(i.savingsAmount) FROM Income AS i WHERE i.addedBy.id=:id")
    Long findSavingsAmountByUser(Long id);

}
