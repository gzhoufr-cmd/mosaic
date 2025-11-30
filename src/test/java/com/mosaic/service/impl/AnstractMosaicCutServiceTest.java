package com.mosaic.service.impl;

import com.mosaic.domain.Cut;

public abstract class AnstractMosaicCutServiceTest
{
    protected boolean validateCut(Cut cut, int m, int n)
    {
        int cnt = 0;
        var visited = new boolean[m][n];
        for (var p : cut.getPieces())
        {
            for (int i = p.getSx(); i <= p.getEx(); ++i)
            {
                for (int j = p.getSy(); j <= p.getEy(); ++j)
                {
                    ++cnt;
                    if (visited[i][j])
                    {
                        return false;
                    }
                    visited[i][j] = true;
                }
            }

        }
        return cnt == cut.getTotalSurface();
    }
}
