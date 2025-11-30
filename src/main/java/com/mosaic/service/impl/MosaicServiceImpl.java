package com.mosaic.service.impl;

import org.springframework.stereotype.Service;

import com.mosaic.domain.Cut;
import com.mosaic.dto.enumeration.MosaicCutAlgorithm;
import com.mosaic.helper.MosaicReader;
import com.mosaic.repository.CutRepository;
import com.mosaic.service.MosaicCutAlgorithmService;
import com.mosaic.service.MosaicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MosaicServiceImpl implements MosaicService
{
    private final CutRepository cutRepository;

    private final MosaicCutAlgorithmFactory algoFactory;

    @Override
    public Cut cut(MosaicCutAlgorithm algoName)
    {
        log.info("Starting to cut the mosaic!");
        long startTime = System.currentTimeMillis();
        MosaicReader.Mosaic mosaic = MosaicReader.readFile();
        MosaicCutAlgorithmService algo = algoFactory.getAlgorithm(algoName);
        Cut mosaicCut = algo.generate(mosaic);
        log.info("Mosaic cut ended in {} ms", System.currentTimeMillis() - startTime);
        saveCut(mosaicCut);
        return mosaicCut;
    }

    private void saveCut(Cut mosaicCut)
    {
        Thread.ofVirtual().start(() -> {
            cutRepository.save(mosaicCut);
            log.info("Mosaic cut saveded in database.");
        });
    }
}
