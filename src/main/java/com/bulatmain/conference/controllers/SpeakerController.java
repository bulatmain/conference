package com.bulatmain.conference.controllers;

import com.bulatmain.conference.entities.Speaker;
import com.bulatmain.conference.services.SpeakerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/speaker")
@AllArgsConstructor
public class SpeakerController {
    @Autowired
    private SpeakerService speakerService;

    @GetMapping
    public List<Speaker> getSpeakers() {
        return speakerService.getSpeakers();
    }

    @GetMapping("/{speaker_id}")
    public Speaker getSpeakerById(@PathVariable("speaker_id") Long speakerId) {
        return speakerService.getSpeakerById(speakerId);
    }

    @PostMapping
    public Long registerNewSpeaker(@RequestBody Speaker speaker) {
        speakerService.saveSpeaker(speaker);
        return speaker.getId();
    }

//    @PutMapping
//    @Transactional
//    public void updateSpeaker(@RequestBody Speaker speaker) {
//
//    }

    @DeleteMapping("/{speaker_id}")
    public void deleteSpeaker(@PathVariable("speaker_id") Long speakerId) {
        speakerService.deleteSpeaker(speakerId);
    }
}
