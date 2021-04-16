package com.familyapp.repositories;

import com.familyapp.models.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

    List<Event> findAllByAddedFrom_Username(String username);
}
