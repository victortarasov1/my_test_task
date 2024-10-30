package org.example.model;

import java.time.LocalDate;

public record Query(
        String serviceId,
        String questionTypeId,
        String responseType,
        LocalDate dateFrom,
        LocalDate dateTo
) {
    public Query {
        if(isFieldIncorrect(serviceId) || isFieldIncorrect(questionTypeId))
            throw new RuntimeException("wrong field!");
    }

    private boolean isFieldIncorrect(String field) {
        return field.contains("*") && !field.equals("*");
    }
}
