package com.familyapp.repositories;

import com.familyapp.models.entities.Family;
import com.familyapp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepo extends JpaRepository<Family, Long> {
    Optional<Family> findByEmail(String familyEmail);
}
