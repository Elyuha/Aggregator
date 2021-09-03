package com.example.TaxiBest.ResAndReq;

import lombok.Data;

@Data
public class ResponseCreate {
    private Long order_id;
    private int cost;
    private Long transaction;
    private String auto;
    private String number;

}
