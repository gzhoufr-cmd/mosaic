package com.mosaic.service;

import com.mosaic.domain.Cut;
import com.mosaic.dto.enumeration.MosaicCutAlgorithm;

public interface MosaicService
{
    public Cut cut(MosaicCutAlgorithm algo);
}
