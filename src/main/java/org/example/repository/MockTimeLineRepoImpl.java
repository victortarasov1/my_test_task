package org.example.repository;

import org.example.model.WaitingTimeline;

import java.util.ArrayList;
import java.util.List;

public class MockTimeLineRepoImpl implements TimeLineRepo {
    private final List<WaitingTimeline> registry = new ArrayList<>();
    @Override
    public void save(WaitingTimeline timeline) {
        registry.add(timeline);
    }

    @Override
    public List<WaitingTimeline> findAll() {
        return new ArrayList<>(registry);
    }
}
