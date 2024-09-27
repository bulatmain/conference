package com.bulatmain.conference.controllers;

import com.bulatmain.conference.dto.ConferenceDTO;
import com.bulatmain.conference.entities.Conference;
import com.bulatmain.conference.services.ConferenceService;
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

@WebMvcTest(controllers = ConferenceController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:test.properties")
public class ConferenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConferenceService conferenceService;

    @Autowired
    private ObjectMapper objectMapper;

    Conference conference;
    ConferenceDTO conferenceDTO;

    final String path = "/api/v1/conference";

    @BeforeEach
    public void init() {
        String conferenceName = "HardCode 2024";
        conference = Conference.builder()
                .name(conferenceName).build();
        conferenceDTO = ConferenceDTO.builder()
                .name(conferenceName).build();
    }


    @Test
    public void RegisterNewConference_ReturnsCreated() throws Exception {
        given(conferenceService.saveConference(ArgumentMatchers.any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(conferenceDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }



}
