package com.bestaggregator.aggregator.connectros;

import com.bestaggregator.aggregator.request.CancelRequest;
import com.bestaggregator.aggregator.request.CreateRequest;
import com.bestaggregator.aggregator.request.GetPricesRequest;
import com.bestaggregator.aggregator.response.CancelResponse;
import com.bestaggregator.aggregator.response.CreateResponse;
import com.bestaggregator.aggregator.response.GetPricesResponse;

public interface Connector {
    CreateResponse createOrder(CreateRequest request);
    CancelResponse cancelOrder(CancelRequest request);
    GetPricesResponse getPrices(GetPricesRequest request);

}
