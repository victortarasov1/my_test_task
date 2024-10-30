package org.example.service;

import org.example.model.Query;
import org.example.model.WaitingTimeline;
import org.example.repository.TimeLineRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TimeLineServiceImplTest {
    private TimeLineRepo repo;
    private TimeLineService service;

    @BeforeEach
    public void setUp() {
        repo = mock(TimeLineRepo.class);
        service = new TimeLineServiceImpl(repo);
    }
    @Test
    public void testGetAverageWhenMatchesFound() {
        var query = new Query("1", "*", "P",
                LocalDate.of(2012, 1, 1), LocalDate.of(2012, 12, 29));

        var timelines = List.of(
                new WaitingTimeline("1.1", "8.15.1", "P", LocalDate.of(2012 ,10,15 ), 83),
                new WaitingTimeline("1.1", "10.1", "P", LocalDate.of(2012 ,12,1 ), 65)
        );

        when(repo.findAll()).thenReturn(timelines);

        var result = service.getAverage(query);


        assertEquals("74.0", result);
    }

    @Test
    public void testGetAverageWhenNoMatchesFound() {
        var query = new Query("1", "*", "P",
                LocalDate.of(2012, 1, 1), LocalDate.of(2012, 12, 29));

        when(repo.findAll()).thenReturn(List.of());


        var result = service.getAverage(query);

        assertEquals("-", result);
    }
}