package com.bulatmain.conference.repositories;

import com.bulatmain.conference.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConferenceRepository
        extends JpaRepository<Conference, Long> {
    @Query("SELECT c FROM Conference c WHERE c.name = ?1")
    Optional<Conference> findConferenceByName(String name);
}
