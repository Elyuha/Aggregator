package com.bestaggregator.aggregator.response;

import lombok.Data;

@Data
public class CreateResponse {
    private Long order_id;
    private int price;
    private Long transaction;
    private String agr;
    private String modelA;
    private String numberA;
}
