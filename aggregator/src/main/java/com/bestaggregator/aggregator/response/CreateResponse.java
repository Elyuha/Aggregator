package com.bestaggregator.aggregator.response;

import lombok.Data;

@Data
public class CreateResponse {
    private Long order_id;
    private int price;
    private Long transaction;
    private int service_id;
    private String modelA;
    private String numberA;
}
