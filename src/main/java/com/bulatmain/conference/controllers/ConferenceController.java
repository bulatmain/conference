package com.bulatmain.conference.controllers;

import com.bulatmain.conference.dto.ConferenceDTO;
import com.bulatmain.conference.services.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/conference")
@AllArgsConstructor
public class ConferenceController {
    @Autowired
    private ConferenceService ConferenceService;

    @GetMapping
    public ResponseEntity<List<ConferenceDTO>> getConferences() {
        return new ResponseEntity<>(ConferenceService.getConferences(), HttpStatus.OK);
    }

    @GetMapping("/{conference_id}")
    public ResponseEntity<ConferenceDTO> getConferenceById(@PathVariable("id") Long conferenceId) {
        return new ResponseEntity<>(ConferenceService.getConferenceById(conferenceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> registerNewConference(@RequestBody ConferenceDTO conferenceDTO) {
        ConferenceService.saveConference(conferenceDTO);
        return new ResponseEntity<>(conferenceDTO.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ConferenceDTO> updateConference(@RequestBody ConferenceDTO conferenceDTO, @PathVariable("id") Long conferenceId) {
        ConferenceDTO response = ConferenceService.update(conferenceDTO, conferenceId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{conference_id}")
    public ResponseEntity<String> deleteConference(@PathVariable("conference_id") Long ConferenceId) {
        ConferenceService.deleteConference(ConferenceId);
        return new ResponseEntity<>("Conference deleted", HttpStatus.OK);
    }
}
