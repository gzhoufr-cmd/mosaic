package com.mosaic.service.impl;

import com.mosaic.helper.MosaicReader;

public abstract sealed class AbstractMosaicCutAlgorithm permits MosaicCutGreedyAlgorithm, MosaicCutBalancedGreedyAlgorithm
{
    protected int getMinRow(MosaicReader.Mosaic mosaic, int[] w)
    {
        int pos = -1, min = mosaic.n();
        // find the minimum fulfilled row
        for (int i = 0; i < mosaic.m(); ++i)
        {
            int val = w[i];
            if (val < min)
            {
                pos = i;
                min = w[i];
            }
        }
        return pos;
    }

    protected boolean validate(int rs, int cs, int re, int ce, int[][] grids, int l, int surface)
    {
        int cnt = 0;
        for (int i = rs; i < re; ++i)
        {
            for (int j = cs; j < ce; ++j)
            {
                cnt += grids[i][j];
            }
        }
        return cnt >= l && surface - cnt >= l; // W count && B count
    }
}
