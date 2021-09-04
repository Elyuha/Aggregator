package com.example.TaxiLyoha.ResAndReq;

import lombok.Data;

@Data
public class RequestPrices {
    private Long id;
    private Double latitudeStart;
    private Double longitudeStart;
    private Double latitudeFinish;
    private Double longitudeFinish;
}
