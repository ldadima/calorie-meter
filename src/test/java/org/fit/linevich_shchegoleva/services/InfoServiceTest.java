package org.fit.linevich_shchegoleva.services;

import org.fit.linevich_shchegoleva.model.CalculateNormTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InfoServiceTest {
    private final InfoService service = new InfoService();

    @ParameterizedTest
    @EnumSource(CalculateNormTest.class)
    void calculateNorm(CalculateNormTest test) {
        Integer norm = service.calculateNorm(test.getInfo());
        assertEquals(test.getNorm(), norm);
    }
}