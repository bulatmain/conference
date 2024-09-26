package com.bulatmain.conference.controllers;

import com.bulatmain.conference.dto.SpeakerDTO;
import com.bulatmain.conference.entities.Speaker;
import com.bulatmain.conference.services.SpeakerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/speaker")
@AllArgsConstructor
public class SpeakerController {
    @Autowired
    private SpeakerService speakerService;

    @GetMapping
    public ResponseEntity<List<SpeakerDTO>> getSpeakers() {
        return new ResponseEntity<>(speakerService.getSpeakers(), HttpStatus.OK);
    }

    @GetMapping("/{speaker_id}")
    public ResponseEntity<SpeakerDTO> getSpeakerById(@PathVariable("id") Long speakerId) {
        return new ResponseEntity<>(speakerService.getSpeakerById(speakerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> registerNewSpeaker(@RequestBody SpeakerDTO speakerDTO) {
        speakerService.saveSpeaker(speakerDTO);
        return new ResponseEntity<>(speakerDTO.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SpeakerDTO> updateSpeaker(@RequestBody SpeakerDTO speakerDTO, @PathVariable("id") Long speakerId) {
        SpeakerDTO response = speakerService.update(speakerDTO, speakerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{speaker_id}")
    public ResponseEntity<String> deleteSpeaker(@PathVariable("speaker_id") Long speakerId) {
        speakerService.deleteSpeaker(speakerId);
        return new ResponseEntity<>("Speaker deleted", HttpStatus.OK);
    }
}
