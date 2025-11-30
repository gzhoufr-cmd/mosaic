package com.mosaic.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosaic.domain.GreedyCut;
import com.mosaic.domain.Piece;
import com.mosaic.dto.enumeration.MosaicCutAlgorithm;
import com.mosaic.service.MosaicService;

import liquibase.integration.spring.SpringLiquibase;

@WebMvcTest(MosaicController.class)
class MosaicControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockitoSpyBean
    private ObjectMapper mapper;

    @MockitoBean
    private MosaicService service;

    @MockitoBean
    private SpringLiquibase liquibase;

    @Test
    void SuccessTest() throws Exception
    {
        // Given
        var cut = GreedyCut.builder().totalSurface(0).pieces(new ArrayList<Piece>()).build();
        when(service.cut(MosaicCutAlgorithm.GREEDY)).thenReturn(cut);

        // When & Then
        mockMvc.perform(get("/mosaic").param("algo", "GREEDY")).andExpect(status().isOk()).andExpect(content().json("""
                   {
                     "totalSurface" : 0,
                     "algorithm" : "GREEDY",
                     "pieces" : [ ]
                   }
                """));
    }

    @Test
    void Failtest() throws Exception
    {
        // Given
        var cut = GreedyCut.builder().totalSurface(0).pieces(new ArrayList<Piece>()).build();
        when(service.cut(MosaicCutAlgorithm.GREEDY)).thenReturn(cut);

        // When & Then
        mockMvc.perform(get("/mosaic").param("algo", "NOT_EXISTED_ALGO")).andExpect(status().isBadRequest()).andExpect(
                content().string("We only support the following mosaic cut algorithms: GREEDY, BALANCED_GREEDY"));
    }

}
