package com.mosaic.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.mosaic.dto.enumeration.MosaicCutAlgorithm;
import com.mosaic.repository.CutRepository;
import com.mosaic.service.MosaicService;

@SpringBootTest(classes = { MosaicServiceImpl.class, MosaicCutAlgorithmFactory.class, MosaicCutGreedyAlgorithm.class,
        MosaicCutBalancedGreedyAlgorithm.class })
public class MosaicServiceImplTest extends AnstractMosaicCutServiceTest
{

    @Autowired
    private MosaicService service;

    @MockitoBean
    private CutRepository repo;

    @Test
    public void GreedyCutTest()
    {
        var res = service.cut(MosaicCutAlgorithm.GREEDY);
        assertEquals(49314, res.getTotalSurface());
        assertTrue(validateCut(res, 200, 250));
    }

    @Test
    public void BalancedGreedyCutTest()
    {
        var res = service.cut(MosaicCutAlgorithm.BALANCED_GREEDY);
        assertEquals(49236, res.getTotalSurface());
        assertTrue(validateCut(res, 200, 250));
    }
}
