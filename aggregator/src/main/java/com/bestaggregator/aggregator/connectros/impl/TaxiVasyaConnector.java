package com.bestaggregator.aggregator.connectros.impl;

import com.bestaggregator.aggregator.connectros.Connector;
import com.bestaggregator.aggregator.request.CancelRequest;
import com.bestaggregator.aggregator.request.CreateRequest;
import com.bestaggregator.aggregator.request.GetPricesRequest;
import com.bestaggregator.aggregator.response.CancelResponse;
import com.bestaggregator.aggregator.response.CreateResponse;
import com.bestaggregator.aggregator.response.GetPricesResponse;

public class TaxiVasyaConnector implements Connector {
    @Override
    public CreateResponse createOrder(CreateRequest request) {
        return null;
    }

    @Override
    public CancelResponse cancelOrder(CancelRequest request) {
        return null;
    }

    @Override
    public GetPricesResponse getPrices(GetPricesRequest request) {
        return null;
    }
}
