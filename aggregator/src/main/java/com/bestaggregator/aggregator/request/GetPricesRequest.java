package com.bestaggregator.aggregator.request;

import com.bestaggregator.aggregator.data.Place;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class GetPricesRequest {
    private Long id;
    private Place startPlace;
    private Place finishPlace;
    private LocalDate date;
    private LocalTime time;

    public GetPricesRequest(){}
}
