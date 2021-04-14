package com.familyapp.repositories;

import com.familyapp.models.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

    @Query(nativeQuery = true, value = "select income_amount from family_app_db.incomes where added_by_id=:id order by received_on desc limit 1")
    Long findIncomeAmountByUser(@Param("id") Long id);

    @Query("SELECT SUM(i.savingsAmount) FROM Income AS i WHERE i.addedBy.id=:id")
    Long findSavingsAmountByUser(Long id);

}
