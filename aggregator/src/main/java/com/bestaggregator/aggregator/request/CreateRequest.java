package com.bestaggregator.aggregator.request;

import com.bestaggregator.aggregator.data.Place;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateRequest {
    private Long user_id;
    private String agr;
    private String name;
    private Place startPlace;
    private Place finishPlace;
    private LocalDate date;
    private LocalTime time;
}
