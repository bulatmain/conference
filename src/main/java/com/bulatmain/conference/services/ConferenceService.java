package com.bulatmain.conference.services;

import com.bulatmain.conference.dto.ConferenceDTO;
import com.bulatmain.conference.entities.Conference;
import com.bulatmain.conference.repositories.ConferenceRepository;
import com.bulatmain.conference.services.exceptions.NoSuchObjectException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConferenceService {

    @Autowired
    private final ConferenceRepository conferenceRepository;



    public List<ConferenceDTO> getConferences() {
        return conferenceRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public ConferenceDTO getConferenceById(Long ConferenceId) {
        Conference ConferenceById = conferenceRepository
                .findById(ConferenceId)
                .orElseThrow(() -> new NoSuchObjectException("There is no Conference with id " + ConferenceId));
        return mapToDto(ConferenceById);
    }

    public ConferenceDTO saveConference(ConferenceDTO conferenceDTO) {
        if (availableToSave(conferenceDTO)) {
            Conference Conference = conferenceRepository.save(mapToEntity(conferenceDTO));
            conferenceDTO.setId(Conference.getId());
            return conferenceDTO;
        } else {
            throw new IllegalStateException("Name taken");
        }
    }

    public ConferenceDTO update(ConferenceDTO conferenceDTO, Long ConferenceId) {
        Conference Conference = conferenceRepository.
                findById(ConferenceId)
                .orElseThrow(() -> new NoSuchObjectException("There is no Conference with id " + ConferenceId));
        Conference.setName(conferenceDTO.getName());

        Conference updated = conferenceRepository.save(Conference);
        return mapToDto(updated);
    }

    public void deleteConference(Long ConferenceId) {
        if (conferenceRepository.existsById(ConferenceId)) {
            conferenceRepository.deleteById(ConferenceId);
        } else {
            throw new NoSuchObjectException("There are no Conference with such id");
        }
    }

    private boolean availableToSave(ConferenceDTO conferenceDTO) {
        Optional<Conference> ConferenceByName = conferenceRepository
                .findConferenceByName(conferenceDTO.getName());
        return ConferenceByName.isEmpty();
    }

    private ConferenceDTO mapToDto(Conference Conference) {
        return ConferenceDTO.builder()
                .id(Conference.getId())
                .name(Conference.getName()).build();
    }

    private Conference mapToEntity(ConferenceDTO conferenceDTO) {
        return Conference.builder()
                .name(conferenceDTO.getName()).build();
    }

}

