package com.bulatmain.conference.services;

import com.bulatmain.conference.dto.SpeakerDTO;
import com.bulatmain.conference.entities.Speaker;
import com.bulatmain.conference.repositories.SpeakerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpeakerServiceTest {

    @Mock
    private SpeakerRepository speakerRepository;

    @InjectMocks
    private SpeakerService speakerService;

    @Test
    public void GetSpeakers_ReturnsSpeakerDTO() {
        Speaker speaker = Mockito.mock(Speaker.class);
        List<Speaker> speakers = List.of(speaker);

        when(speakerRepository.findAll()).thenReturn(speakers);

        List<SpeakerDTO> response = speakerService.getSpeakers();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.size(), 1);
    }

    @Test
    public void GetSpeakerById_ReturnsSpeakerDTO() {
        Speaker speaker = Mockito.mock(Speaker.class);

        when(speakerRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(speaker));

        try {
            SpeakerDTO response = speakerService.getSpeakerById(1L);
            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void SaveSpeaker_ReturnsSpeakerDTO() {
        Speaker speaker = Speaker.builder()
                .name("John Snow").build();
        SpeakerDTO speakerDTO = SpeakerDTO.builder()
                .name("John Snow").build();


        when(speakerRepository.save(Mockito.any(Speaker.class)))
                .thenReturn(speaker);

        try {
            SpeakerDTO savedSpeakerDTO = speakerService.saveSpeaker(speakerDTO);
            Assertions.assertNotNull(savedSpeakerDTO);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void UpdateSpeaker_ReturnsSpeakerDTO() {
        Speaker speaker = Speaker.builder()
                .name("John Snow").build();
        SpeakerDTO speakerDTO = SpeakerDTO.builder()
                .name("John Snow").build();

        when(speakerRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(speaker));
        when(speakerRepository.save(Mockito.any(Speaker.class)))
                .thenReturn(speaker);

        try {
            SpeakerDTO savedSpeakerDTO = speakerService.update(speakerDTO, 1L);
            Assertions.assertNotNull(savedSpeakerDTO);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void DeleteSpeakerById_ReturnsSpeakerDTO() {
        Speaker speaker = Mockito.mock(Speaker.class);

        when(speakerRepository.existsById(Mockito.any(Long.class)))
                .thenReturn(true);
        Mockito.doNothing()
                .when(speakerRepository)
                .deleteById(Mockito.any(Long.class));

        Assertions.assertAll(() ->
                speakerService.deleteSpeaker(1L));
    }


}
