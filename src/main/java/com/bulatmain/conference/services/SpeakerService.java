package com.bulatmain.conference.services;

import com.bulatmain.conference.dto.SpeakerDTO;
import com.bulatmain.conference.entities.Speaker;
import com.bulatmain.conference.repositories.SpeakerRepository;
import com.bulatmain.conference.services.exceptions.NoSuchObjectException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpeakerService {
    @Autowired
    private final SpeakerRepository speakerRepository;

    public List<SpeakerDTO> getSpeakers() {
        return speakerRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public SpeakerDTO getSpeakerById(Long speakerId) {
        Speaker speakerById = speakerRepository
                .findById(speakerId)
                .orElseThrow(() -> new NoSuchObjectException("There is no speaker with id " + speakerId));
        return mapToDto(speakerById);
    }

    public SpeakerDTO saveSpeaker(SpeakerDTO speakerDTO) {
        if (availableToSave(speakerDTO)) {
            Speaker speaker = speakerRepository.save(mapToEntity(speakerDTO));
            speakerDTO.setId(speaker.getId());
            return speakerDTO;
        } else {
            throw new IllegalStateException("Name taken");
        }
    }

    public SpeakerDTO update(SpeakerDTO speakerDTO, Long speakerId) {
        Speaker speaker = speakerRepository.
                findById(speakerId)
                .orElseThrow(() -> new NoSuchObjectException("There is no speaker with id " + speakerId));
        speaker.setName(speakerDTO.getName());

        Speaker updated = speakerRepository.save(speaker);
        return mapToDto(updated);
    }

    public void deleteSpeaker(Long speakerId) {
        if (speakerRepository.existsById(speakerId)) {
            speakerRepository.deleteById(speakerId);
        } else {
            throw new NoSuchObjectException("There are no speaker with such id");
        }
    }

    private boolean availableToSave(SpeakerDTO speakerDTO) {
        Optional<Speaker> speakerByName = speakerRepository
                .findSpeakerByName(speakerDTO.getName());
        return speakerByName.isEmpty();
    }

    private SpeakerDTO mapToDto(Speaker speaker) {
        return SpeakerDTO.builder()
                .id(speaker.getId())
                .name(speaker.getName()).build();
    }

    private Speaker mapToEntity(SpeakerDTO speakerDTO) {
        return Speaker.builder()
                .name(speakerDTO.getName()).build();
    }

}
