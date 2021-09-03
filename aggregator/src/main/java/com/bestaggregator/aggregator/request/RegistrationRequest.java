package com.bestaggregator.aggregator.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {
    @NotEmpty
    private String phone;

    @NotEmpty
    private String password;
}
