package com.mosaic.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mosaic.domain.Cut;
import com.mosaic.domain.GreedyCut;
import com.mosaic.domain.Piece;
import com.mosaic.dto.enumeration.MosaicCutAlgorithm;
import com.mosaic.helper.MosaicReader;
import com.mosaic.helper.MosaicReader.Mosaic;
import com.mosaic.service.MosaicCutAlgorithmService;

@Service
public final class MosaicCutGreedyAlgorithm extends AbstractMosaicCutAlgorithm implements MosaicCutAlgorithmService
{
    @Override
    public Cut generate(Mosaic mosaic)
    {
        int pos;
        var w = new int[mosaic.m()];
        Cut res = GreedyCut.builder().totalSurface(0).pieces(new ArrayList<Piece>()).build();
        while ((pos = getMinRow(mosaic, w)) > -1)
        {
            greedy(mosaic, w, res, pos, w[pos]);
        }
        return res;
    }

    private Cut greedy(MosaicReader.Mosaic mosaic, int[] w, Cut res, int pos, int min)
    {
        // find out maximum rows we can form rectangle starting from current row
        int end = pos;
        while (end < mosaic.m() && w[end] == min)
        {
            ++end;
        }
        // try to form a maximum valid rectangle surface
        int surface = Math.min(mosaic.h(), (end - pos) * (mosaic.n() - min));
        for (int i = end - pos; i > 0; --i)
        {
            if (surface % i != 0)
                continue;
            int c = surface / i;
            if (c + min > mosaic.n())
                continue;
            if (!validate(pos, min, pos + i, min + c, mosaic.grid(), mosaic.l(), surface))
                continue;
            // a valid piece is found
            for (int k = pos; k < pos + i; ++k)
            {
                w[k] += c; // update status
            }
            var piece = Piece.builder().sx(pos).ex(pos + i - 1).sy(min).ey(min + c - 1).surface(surface).cut(res)
                    .build();
            res.getPieces().add(piece);
            res.setTotalSurface(res.getTotalSurface() + surface);
            return res;
        }
        w[pos]++; // if no valid piece found, march one step forward
        return res;
    }

    @Override
    public MosaicCutAlgorithm getName()
    {
        return MosaicCutAlgorithm.GREEDY;
    }

}
