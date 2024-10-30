package org.example.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WaitingTimelineTest {
    @Test
    void testMatchesWithExactServiceIdAndQuestionType() {
        var timeline = new WaitingTimeline("1.1", "8", "P", LocalDate.of(2012, 10, 15), 83);
        var query = new Query("1.1", "8", "P", LocalDate.of(2012, 10, 10), LocalDate.of(2012, 10, 20));

        assertTrue(timeline.isMatches(query));
    }

    @Test
    void testMatchesWithWildcardServiceId() {
        var timeline = new WaitingTimeline("1.2", "8", "P", LocalDate.of(2012, 10, 15), 83);
        var query = new Query("*", "8", "P", LocalDate.of(2012, 10, 10), LocalDate.of(2012, 10, 20));

        assertTrue(timeline.isMatches(query));
    }

    @Test
    void testMatchesWithWildcardQuestionType() {
        var timeline = new WaitingTimeline("1.1", "10", "P", LocalDate.of(2012, 10, 15), 83);
        var query = new Query("1.1", "*", "P", LocalDate.of(2012, 10, 10), LocalDate.of(2012, 10, 20));

        assertTrue(timeline.isMatches(query));
    }

    @Test
    void testDoesNotMatchWithDifferentResponseType() {
        var timeline = new WaitingTimeline("1.1", "8", "N", LocalDate.of(2012, 10, 15), 83);
        var query = new Query("1.1", "8", "P", LocalDate.of(2012, 10, 10), LocalDate.of(2012, 10, 20));

        assertFalse(timeline.isMatches(query));
    }

    @Test
    void testMatchesOnDateBoundary() {
        var timeline = new WaitingTimeline("1.1", "8", "P", LocalDate.of(2012, 10, 10), 83);
        var query = new Query("1.1", "8", "P", LocalDate.of(2012, 10, 10), LocalDate.of(2012, 10, 15));

        assertTrue(timeline.isMatches(query));
    }

    @Test
    void testDoesNotMatchOutsideDateRange() {
        var timeline = new WaitingTimeline("1.1", "8", "P", LocalDate.of(2012, 10, 16), 83);
        var query = new Query("1.1", "8", "P", LocalDate.of(2012, 10, 10), LocalDate.of(2012, 10, 15));

        assertFalse(timeline.isMatches(query));
    }

    @Test
    void testDoesNotMatchWithDifferentServiceId() {
        var timeline = new WaitingTimeline("2.1", "8", "P", LocalDate.of(2012, 10, 15), 83);
        var query = new Query("1.1", "8", "P", LocalDate.of(2012, 10, 10), LocalDate.of(2012, 10, 20));

        assertFalse(timeline.isMatches(query));
    }

    @Test
    void testDoesNotMatchIfDateToIsBeforeDateFrom() {
        var timeline = new WaitingTimeline("1.1", "8", "P", LocalDate.of(2012, 10, 15), 83);
        var query = new Query("1.1", "8", "P", LocalDate.of(2012, 10, 20), LocalDate.of(2012, 10, 10));

        assertFalse(timeline.isMatches(query));
    }
}