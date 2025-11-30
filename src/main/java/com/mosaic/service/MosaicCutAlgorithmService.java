package com.mosaic.service;

import com.mosaic.domain.Cut;
import com.mosaic.dto.enumeration.MosaicCutAlgorithm;
import com.mosaic.helper.MosaicReader;

public interface MosaicCutAlgorithmService
{
    public Cut generate(MosaicReader.Mosaic mosaic);

    public MosaicCutAlgorithm getName();
}
