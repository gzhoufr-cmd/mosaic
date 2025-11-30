package com.mosaic.dto;

import java.util.ArrayList;
import java.util.List;

import com.mosaic.domain.Cut;
import com.mosaic.dto.enumeration.MosaicCutAlgorithm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CutDto
{
    private int totalSurface;

    private MosaicCutAlgorithm algorithm;

    private List<PieceDto> pieces;

    public static CutDto map(Cut cut)
    {
        var pieces = new ArrayList<PieceDto>();
        for (var p : cut.getPieces())
        {
            var start = CoordinateDto.builder().x(p.getSx()).y(p.getSy()).build();
            var end = CoordinateDto.builder().x(p.getEx()).y(p.getEy()).build();
            var piece = PieceDto.builder().start(start).end(end).surface(p.getSurface()).build();
            pieces.add(piece);
        }
        return CutDto.builder().totalSurface(cut.getTotalSurface()).pieces(pieces).build();
    }
}
