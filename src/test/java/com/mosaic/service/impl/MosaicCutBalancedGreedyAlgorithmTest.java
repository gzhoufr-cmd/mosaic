package com.mosaic.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mosaic.helper.MosaicReader;

@SpringBootTest(classes = { MosaicCutBalancedGreedyAlgorithm.class })
class MosaicCutBalancedGreedyAlgorithmTest extends AnstractMosaicCutServiceTest
{

    @Autowired
    private MosaicCutBalancedGreedyAlgorithm algo;

    @Test
    public void SimpleCutTest()
    {
        // Given
        int[][] grid = { { 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1 } };
        var mosaic = new MosaicReader.Mosaic(3, 5, 1, 6, grid);
        int[][] grid2 = { { 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1 }, { 1, 1, 1, 1, 1 } };
        var mosaic2 = new MosaicReader.Mosaic(3, 5, 1, 6, grid2);
        int[][] grid3 = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1 }, { 1, 1, 1, 1, 1 } };
        var mosaic3 = new MosaicReader.Mosaic(3, 5, 1, 6, grid3);
        int[][] grid4 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        var mosaic4 = new MosaicReader.Mosaic(4, 3, 1, 6, grid4);

        // When
        var res = algo.generate(mosaic);
        var res2 = algo.generate(mosaic2);
        var res3 = algo.generate(mosaic3);
        var res4 = algo.generate(mosaic4);

        // Then
        assertEquals(12, res.getTotalSurface()); // maximum: 15
        assertTrue(validateCut(res, 3, 5));

        assertEquals(12, res2.getTotalSurface());
        assertTrue(validateCut(res2, 3, 5));

        assertEquals(5, res3.getTotalSurface()); // maximum: 6
        assertTrue(validateCut(res3, 3, 5));

        assertEquals(6, res4.getTotalSurface()); // maximum: 12
        assertTrue(validateCut(res4, 4, 3));
    }

    @Test
    public void LittleTrickyCutTest()
    {
        // Given
        int[][] grid = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 0 } };
        var mosaic = new MosaicReader.Mosaic(3, 5, 3, 6, grid);

        // When
        var res = algo.generate(mosaic);

        // Then
        assertEquals(6, res.getTotalSurface());
        assertTrue(validateCut(res, 3, 5));
    }

    @Test
    public void CutTest()
    {
        // Given
        int[][] grid = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0, 1, 1, 1, 0, 1 },
                { 1, 0, 0, 1, 0, 1, 1, 1, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
        var mosaic = new MosaicReader.Mosaic(4, 10, 1, 6, grid);

        // When
        var res = algo.generate(mosaic);

        // Then
        assertEquals(30, res.getTotalSurface()); // maximum: 40
        assertTrue(validateCut(res, 4, 10));
    }
}
