package com.bestaggregator.aggregator.request;

import lombok.Data;

@Data
public class CancelRequest {
    private Long order_id;
    private Long transaction;
    private Long taxi_id;
}
