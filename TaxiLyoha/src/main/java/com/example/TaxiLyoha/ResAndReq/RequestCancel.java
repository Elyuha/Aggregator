package com.example.TaxiLyoha.ResAndReq;

import lombok.Data;

@Data
public class RequestCancel {
    private Long order_id;
    private Long transaction;
}
