package org.example.repository;

import org.example.model.WaitingTimeline;

import java.util.List;

public interface TimeLineRepo {
    void save(WaitingTimeline timeline);

    List<WaitingTimeline> findAll();
}
