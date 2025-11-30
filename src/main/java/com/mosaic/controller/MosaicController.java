package com.mosaic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosaic.domain.Cut;
import com.mosaic.dto.CutDto;
import com.mosaic.dto.enumeration.MosaicCutAlgorithm;
import com.mosaic.service.MosaicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MosaicController
{
    private final MosaicService service;

    private final ObjectMapper mapper;

    @GetMapping("/mosaic")
    public String cutMosaic(@RequestParam(defaultValue = "GREEDY") MosaicCutAlgorithm algo)
            throws JsonProcessingException
    {
        log.info("{} algorithm will be used to do the mosaic cut", algo);
        Cut cut = service.cut(algo);
        CutDto dto = CutDto.map(cut);
        dto.setAlgorithm(algo);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleAnyUncaughtException(MethodArgumentTypeMismatchException e)
    {
        log.debug("User choose the non-existed {} algorithm to do the mosaic cut.", e.getValue());
        var sb = new StringBuilder();
        sb.append("We only support the following mosaic cut algorithms: ");
        for (var value : MosaicCutAlgorithm.values())
        {
            sb.append(value + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

}
