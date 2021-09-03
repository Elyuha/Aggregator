package com.bestaggregator.aggregator.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Place {
    private double latitude;
    private double longitude;

    public boolean inRange(){
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180)
            return false;
        return true;
    }
}
