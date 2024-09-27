package com.bulatmain.conference.services;

import com.bulatmain.conference.dto.ConferenceDTO;
import com.bulatmain.conference.entities.Conference;
import com.bulatmain.conference.repositories.ConferenceRepository;
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
public class ConferenceServiceTest {

    @Mock
    private ConferenceRepository conferenceRepository;

    @InjectMocks
    private ConferenceService conferenceService;

    @Test
    public void GetConferences_ReturnsConferenceDTO() {
        Conference conference = Mockito.mock(Conference.class);
        List<Conference> conferences = List.of(conference);

        when(conferenceRepository.findAll()).thenReturn(conferences);

        List<ConferenceDTO> response = conferenceService.getConferences();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.size(), 1);
    }

    @Test
    public void GetConferenceById_ReturnsConferenceDTO() {
        Conference conference = Mockito.mock(Conference.class);

        when(conferenceRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(conference));

        try {
            ConferenceDTO response = conferenceService.getConferenceById(1L);
            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void SaveConference_ReturnsConferenceDTO() {
        Conference conference = Conference.builder()
                .name("HardCode 2024").build();
        ConferenceDTO conferenceDTO = ConferenceDTO.builder()
                .name("HardCode 2024").build();


        when(conferenceRepository.save(Mockito.any(Conference.class)))
                .thenReturn(conference);

        try {
            ConferenceDTO savedConferenceDTO = conferenceService.saveConference(conferenceDTO);
            Assertions.assertNotNull(savedConferenceDTO);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void UpdateConference_ReturnsConferenceDTO() {
        Conference conference = Conference.builder()
                .name("HardCode 2024").build();
        ConferenceDTO conferenceDTO = ConferenceDTO.builder()
                .name("HardCode 2024").build();

        when(conferenceRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(conference));
        when(conferenceRepository.save(Mockito.any(Conference.class)))
                .thenReturn(conference);

        try {
            ConferenceDTO savedConferenceDTO = conferenceService.update(conferenceDTO, 1L);
            Assertions.assertNotNull(savedConferenceDTO);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void DeleteConferenceById_ReturnsConferenceDTO() {
        Conference conference = Mockito.mock(Conference.class);

        when(conferenceRepository.existsById(Mockito.any(Long.class)))
                .thenReturn(true);
        Mockito.doNothing()
                .when(conferenceRepository)
                .deleteById(Mockito.any(Long.class));

        Assertions.assertAll(() ->
                conferenceService.deleteConference(1L));
    }


}
