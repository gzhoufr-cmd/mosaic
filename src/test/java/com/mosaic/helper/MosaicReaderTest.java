package com.mosaic.helper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MosaicReaderTest
{
    @Test
    public void ReadFileTest()
    {
        // Given & When
        var mosaic = MosaicReader.readFile();

        // Then
        assertThat(mosaic.m()).isEqualTo(200);
        assertThat(mosaic.n()).isEqualTo(250);
        assertThat(mosaic.l()).isEqualTo(4);
        assertThat(mosaic.h()).isEqualTo(12);
        assertThat(mosaic.grid()[0][0]).isEqualTo(1);
        assertThat(mosaic.grid()[199][249]).isEqualTo(1);
    }

}
