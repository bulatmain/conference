package com.bulatmain.conference.services;

import com.bulatmain.conference.entities.Speaker;
import com.bulatmain.conference.repositories.SpeakerRepository;
import com.bulatmain.conference.services.exceptions.NoSuchObjectException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpeakerService {
    @Autowired
    private final SpeakerRepository speakerRepository;

    public List<Speaker> getSpeakers() {
        return speakerRepository.findAll();
    }

    public Speaker getSpeakerById(Long speakerId) {
        Optional<Speaker> speakerById = speakerRepository.findById(speakerId);
        if (speakerById.isPresent()) {
            return speakerById.get();
        } else {
            throw new NoSuchObjectException("There are no speaker with such id");
        }
    }

    public void saveSpeaker(Speaker speaker) {
        Optional<Speaker> speakerByName =
                speakerRepository.findSpeakerByName(speaker.getName());
        if (speakerByName.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        speakerRepository.save(speaker);
    }

    public void deleteSpeaker(Long speakerId) {
        if (speakerRepository.existsById(speakerId)) {
            speakerRepository.deleteById(speakerId);
        } else {
            throw new NoSuchObjectException("There are no speaker with such id");
        }
    }

}
