package com.bulatmain.conference.controllers;

import com.bulatmain.conference.dto.SpeakerDTO;
import com.bulatmain.conference.entities.Speaker;
import com.bulatmain.conference.services.SpeakerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = SpeakerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:test.properties")
public class SpeakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpeakerService speakerService;

    @Autowired
    private ObjectMapper objectMapper;

    Speaker speaker;
    SpeakerDTO speakerDTO;

    final String path = "/api/v1/speaker";

    @BeforeEach
    public void init() {
        String speakerName = "John Snow";
        speaker = Speaker.builder()
                .name(speakerName).build();
        speakerDTO = SpeakerDTO.builder()
                .name(speakerName).build();
    }


    @Test
    public void RegisterNewSpeaker_ReturnsCreated() throws Exception {
        given(speakerService.saveSpeaker(ArgumentMatchers.any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(speakerDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }



}
