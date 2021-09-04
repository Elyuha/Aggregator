package com.bestaggregator.aggregator.response;

import lombok.Data;

@Data
public class CancelResponse {
    private Long order_id;
    private int price;
    private Long transaction;
    private String agr;
}
