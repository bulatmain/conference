package com.bulatmain.conference.repositories;

import com.bulatmain.conference.entities.Speaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SpeakerRepositoryTest {
    @Autowired
    private SpeakerRepository speakerRepository;

    @Test
    public void SaveAll_ReturnsSavedSpeaker() {
        Speaker speaker = Speaker.builder()
                .name("Aslan Mukliakhmetov").build();

        Speaker saved = speakerRepository.save(speaker);

        Assertions.assertNotNull(saved);
        Assertions.assertTrue(saved.getId() > 0);
    }

    @Test
    public void WhenSavedTwo_GetAll_ReturnsTwo() {
        Speaker speakerOne = Speaker.builder()
                .name("Aslan Mukliakhmetov").build();
        Speaker speakerTwo = Speaker.builder()
                .name("Khalid Donnel").build();

        speakerRepository.save(speakerOne);
        speakerRepository.save(speakerTwo);

        List<Speaker> speakers = speakerRepository.findAll();

        Assertions.assertNotNull(speakers);
        Assertions.assertEquals(speakers.size(), 2);
    }

    @Test
    public void WhenSaved_FindById_ReturnsSpeaker() {
        Speaker speaker = Speaker.builder()
                .name("Aslan Mukliakhmetov").build();

        speakerRepository.save(speaker);

        Speaker speakerById = speakerRepository.findById(speaker.getId()).get();

        Assertions.assertNotNull(speakerById);
    }

    @Test
    public void WhenSaved_FindByName_ReturnsSavedSpeaker() {
        Speaker speaker = Speaker.builder()
                .name("Aslan Mukliakhmetov").build();

        speakerRepository.save(speaker);

        Speaker speakerById = speakerRepository.findSpeakerByName(speaker.getName()).get();

        Assertions.assertNotNull(speakerById);
    }

    @Test
    public void WhenSaved_UpdateSpeaker_ReturnsSavedSpeaker() {
        Speaker speaker = Speaker.builder()
                .name("Aslan Mukliakhmetov").build();

        speakerRepository.save(speaker);

        Speaker speakerById = speakerRepository.findSpeakerByName(speaker.getName()).get();
        speakerById.setName("Khalid Donnel");

        Speaker speakerUpdated = speakerRepository.save(speakerById);

        Assertions.assertNotNull(speakerUpdated);
        Assertions.assertNotNull(speakerUpdated.getName());
    }

    @Test
    public void WhenSaved_DeleteSpeaker_ReturnsEmpty() {
        Speaker speaker = Speaker.builder()
                .name("Aslan Mukliakhmetov").build();

        speakerRepository.delete(speaker);

        Assertions.assertEquals(speakerRepository.count(), 0);
        Assertions.assertNull(speaker.getId());
    }
}
