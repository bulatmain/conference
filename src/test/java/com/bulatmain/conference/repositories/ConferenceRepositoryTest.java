package com.bulatmain.conference.repositories;

import com.bulatmain.conference.entities.Conference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ConferenceRepositoryTest {
    @Autowired
    private ConferenceRepository conferenceRepository;

    @Test
    public void SaveAll_ReturnsSavedConference() {
        Conference conference = Conference.builder()
                .name("HardCode 2024").build();

        Conference saved = conferenceRepository.save(conference);

        Assertions.assertNotNull(saved);
        Assertions.assertTrue(saved.getId() > 0);
    }

    @Test
    public void WhenSavedTwo_GetAll_ReturnsTwo() {
        Conference conferenceOne = Conference.builder()
                .name("HardCode 2024").build();
        Conference conferenceTwo = Conference.builder()
                .name("Young&Yandex").build();

        conferenceRepository.save(conferenceOne);
        conferenceRepository.save(conferenceTwo);

        List<Conference> conferences = conferenceRepository.findAll();

        Assertions.assertNotNull(conferences);
        Assertions.assertEquals(conferences.size(), 2);
    }

    @Test
    public void WhenSaved_FindById_ReturnsConference() {
        Conference conference = Conference.builder()
                .name("HardCode 2024").build();

        conferenceRepository.save(conference);

        Conference conferenceById = conferenceRepository.findById(conference.getId()).get();

        Assertions.assertNotNull(conferenceById);
    }

    @Test
    public void WhenSaved_FindByName_ReturnsSavedConference() {
        Conference conference = Conference.builder()
                .name("HardCode 2024").build();

        conferenceRepository.save(conference);

        Conference conferenceById = conferenceRepository.findConferenceByName(conference.getName()).get();

        Assertions.assertNotNull(conferenceById);
    }

    @Test
    public void WhenSaved_UpdateConference_ReturnsSavedConference() {
        Conference conference = Conference.builder()
                .name("HardCode 2024").build();

        conferenceRepository.save(conference);

        Conference conferenceById = conferenceRepository.findConferenceByName(conference.getName()).get();
        conferenceById.setName("Young&Yandex");

        Conference conferenceUpdated = conferenceRepository.save(conferenceById);

        Assertions.assertNotNull(conferenceUpdated);
        Assertions.assertNotNull(conferenceUpdated.getName());
    }

    @Test
    public void WhenSaved_DeleteConference_ReturnsEmpty() {
        Conference conference = Conference.builder()
                .name("HardCode 2024").build();

        conferenceRepository.delete(conference);

        Assertions.assertEquals(conferenceRepository.count(), 0);
        Assertions.assertNull(conference.getId());
    }
}
