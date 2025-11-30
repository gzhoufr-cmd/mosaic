package com.mosaic.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mosaic.dto.enumeration.MosaicCutAlgorithm;
import com.mosaic.service.MosaicCutAlgorithmService;

@Service
public class MosaicCutAlgorithmFactory
{
    private Map<MosaicCutAlgorithm, MosaicCutAlgorithmService> algorithmMap;

    MosaicCutAlgorithmFactory(List<MosaicCutAlgorithmService> algorithms)
    {
        algorithmMap = algorithms.stream()
                .collect(Collectors.toMap(MosaicCutAlgorithmService::getName, Function.identity()));
    }

    public MosaicCutAlgorithmService getAlgorithm(MosaicCutAlgorithm name)
    {
        return algorithmMap.get(name);
    }
}
