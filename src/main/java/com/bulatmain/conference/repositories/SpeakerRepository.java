package com.bulatmain.conference.repositories;

import com.bulatmain.conference.entities.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeakerRepository
        extends JpaRepository<Speaker, Long> {
    @Query("SELECT s FROM Speaker s WHERE s.name = ?1")
    Optional<Speaker> findSpeakerByName(String name);
}
