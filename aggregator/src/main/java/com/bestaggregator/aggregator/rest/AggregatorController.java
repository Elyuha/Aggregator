package com.bestaggregator.aggregator.rest;

import com.bestaggregator.aggregator.connectros.Connector;
import com.bestaggregator.aggregator.request.CancelRequest;
import com.bestaggregator.aggregator.request.CreateRequest;
import com.bestaggregator.aggregator.request.GetPricesRequest;
import com.bestaggregator.aggregator.response.CancelResponse;
import com.bestaggregator.aggregator.response.CreateResponse;
import com.bestaggregator.aggregator.response.GetPricesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class AggregatorController {

    @Autowired
    List<Connector> list;

    @RequestMapping(value = "/order/getPrices",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetPricesResponse>> getPrices(GetPricesRequest request){
        if (!request.getStartPlace().inRange() || !request.getFinishPlace().inRange())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<GetPricesResponse> responses = new LinkedList<>();

        for (Connector c : list)
            responses.add(c.getPrices(request));

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/createOrder",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateResponse> createOrder(CreateRequest request){
        if (!request.getStartPlace().inRange() || !request.getFinishPlace().inRange())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (list.size() <= request.getTaxi_id())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        CreateResponse order = list.get(Math.toIntExact(request.getTaxi_id())).createOrder(request);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        order.setOrder_id(request.getTaxi_id());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/cancelOrder",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CancelResponse> cancelOrder(CancelRequest request){

        if (list.size() <= request.getTaxi_id())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        CancelResponse order = list.get(Math.toIntExact(request.getTaxi_id())).cancelOrder(request);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
