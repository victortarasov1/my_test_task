package org.example.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class QueryTest {
    @Test
    void testValidQueryCreation() {
        assertDoesNotThrow(
                () -> new Query("1.1", "8", "P",
                        LocalDate.of(2012, 1, 1), LocalDate.of(2012, 12, 31)));
    }

    @Test
    void testInvalidQueryCreation() {
        assertThrows(RuntimeException.class,
                () -> new Query("1.*", "8", "P",
                        LocalDate.of(2012, 1, 1), LocalDate.of(2012, 12, 31)));

        assertThrows(RuntimeException.class,
                () -> new Query("1.1", "8.*", "P",
                        LocalDate.of(2012, 1, 1), LocalDate.of(2012, 12, 31)));

        assertThrows(RuntimeException.class,
                () -> new Query("1.*", "8.*", "P",
                        LocalDate.of(2012, 1, 1), LocalDate.of(2012, 12, 31)));
    }
}