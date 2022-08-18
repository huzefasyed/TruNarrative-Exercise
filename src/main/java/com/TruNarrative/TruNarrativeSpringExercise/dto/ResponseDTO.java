package com.TruNarrative.TruNarrativeSpringExercise.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private int total_results;
    private CompanyDTO[] items;
}

