package org.example.model;

import java.time.LocalDate;

public record WaitingTimeline(
        String serviceId,
        String questionTypeId,
        String responseType,
        LocalDate date,
        int waitingTime
){

    public boolean isMatches(Query query) {
        var isServiceIdMatches = query.serviceId().equals("*") || serviceId.startsWith(query.serviceId());
        var isQuestionTypeIdMatches = query.questionTypeId().equals("*") || questionTypeId.startsWith(query.questionTypeId());
        var isResponseTypeMatches = responseType.equals(query.responseType());
        var isDateMatches = isDateMatches(query);
        return isServiceIdMatches && isQuestionTypeIdMatches && isResponseTypeMatches && isDateMatches;
    }

    private boolean isDateMatches(Query query) {
        if(query.dateTo() != null)
            return (date.isAfter(query.dateFrom()) || date.isEqual(query.dateFrom())) &&
                    (date.isBefore(query.dateTo()) || date.isEqual(query.dateTo()));
        return date.isEqual(query.dateFrom());
    }

}
