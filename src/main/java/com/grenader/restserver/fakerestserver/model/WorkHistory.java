package com.grenader.restserver.fakerestserver.model;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WorkHistory {
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
}
