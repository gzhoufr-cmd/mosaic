package com.mosaic.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.mosaic.domain.GreedyCut;
import com.mosaic.domain.Piece;

@ActiveProfiles("dbtest")
@DataJpaTest(properties = { "spring.datasource.url=jdbc:h2:mem:testdb", "spring.jpa.hibernate.ddl-auto=create-drop" })
class CutRepositoryTest
{

    @Autowired
    private CutRepository repo;

    @Test
    void cutRepoTest()
    {
        // Given
        var piece1 = Piece.builder().sx(0).sy(0).ex(2).ey(3).surface(12).build();
        var piece2 = Piece.builder().sx(0).sy(4).ex(2).ey(7).surface(12).build();
        var pieces = new ArrayList<>(List.of(piece1, piece2));
        var cut = GreedyCut.builder().totalSurface(24).pieces(pieces).build();
        piece1.setCut(cut);
        piece2.setCut(cut);

        // When
        var saved = repo.save(cut);
        var foundOpt = repo.findById(1l);

        // Then
        assertEquals(1l, saved.getId());
        assertEquals(24, saved.getTotalSurface());
        var savedPieces = saved.getPieces();
        assertEquals(2, savedPieces.size());
        var savedPiece1 = savedPieces.get(0);
        assertEquals(1l, savedPiece1.getId());
        assertEquals(0, savedPiece1.getSx());
        assertEquals(0, savedPiece1.getSy());
        assertEquals(2, savedPiece1.getEx());
        assertEquals(3, savedPiece1.getEy());
        assertEquals(12, savedPiece1.getSurface());
        var savedPiece2 = savedPieces.get(1);
        assertEquals(2l, savedPiece2.getId());
        assertEquals(0, savedPiece2.getSx());
        assertEquals(4, savedPiece2.getSy());
        assertEquals(2, savedPiece2.getEx());
        assertEquals(7, savedPiece2.getEy());
        assertEquals(12, savedPiece2.getSurface());

        assertTrue(foundOpt.isPresent());
        var found = foundOpt.get();
        assertEquals(1l, found.getId());
        assertEquals(24, found.getTotalSurface());
        var foundPieces = found.getPieces();
        assertEquals(2, foundPieces.size());
        var foundPiece1 = foundPieces.get(0);
        assertEquals(1l, foundPiece1.getId());
        assertEquals(0, foundPiece1.getSx());
        assertEquals(0, foundPiece1.getSy());
        assertEquals(2, foundPiece1.getEx());
        assertEquals(3, foundPiece1.getEy());
        assertEquals(12, foundPiece1.getSurface());
        var foundPiece2 = foundPieces.get(1);
        assertEquals(2l, foundPiece2.getId());
        assertEquals(0, foundPiece2.getSx());
        assertEquals(4, foundPiece2.getSy());
        assertEquals(2, foundPiece2.getEx());
        assertEquals(7, foundPiece2.getEy());
        assertEquals(12, foundPiece2.getSurface());
    }

}
