package com.bestaggregator.aggregator.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String phone;
    private String password;
}
