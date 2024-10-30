package org.example.service;


import org.example.model.Query;
import org.example.model.WaitingTimeline;
import org.example.repository.TimeLineRepo;

public class TimeLineServiceImpl implements TimeLineService {
    private final TimeLineRepo repository;

    public TimeLineServiceImpl(TimeLineRepo repository) {
        this.repository = repository;
    }

    @Override
    public String getAverage(Query query) {
        var matchedTimeLines = repository.findAll().stream().filter(v -> v.isMatches(query));
        var average = matchedTimeLines.map(WaitingTimeline::waitingTime).mapToInt(v -> v).average();
        return average.isPresent() ? String.valueOf(average.getAsDouble()) : "-";

    }

}
