package com.grenader.restserver.fakerestserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ObjectResponse {
    private int userId;
    private int seriesId;
    private String address;
    private List<String> phoneNumbers;
    private List<WorkHistory> workHistory;

    public ObjectResponse(ObjectRequest objectRequest) {
        userId = objectRequest.getUserId();
        seriesId = objectRequest.getUserId() + 1;

        address = "unknown address";

        workHistory = List.of(new WorkHistory("Company One",
                LocalDate.of(2010, 01, 01),
                LocalDate.of(2012, 06, 01), "manager"));
    }

}
