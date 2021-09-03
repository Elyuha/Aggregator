package com.bestaggregator.aggregator.response;

import com.bestaggregator.aggregator.data.Place;
import lombok.Data;

@Data
public class GetPricesResponse {
    private Place startPlace;
    private Place finishPlace;
    private int price;
    private Long taxi_id;
    private String agr;
}
