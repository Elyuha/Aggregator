package com.example.TaxiLyoha.ResAndReq;

import lombok.Data;

@Data
public class RequestCreate {
    private Long id;
    private String name;
    private Double latitudeStart;
    private Double longitudeStart;
    private Double latitudeFinish;
    private Double longitudeFinish;
}
