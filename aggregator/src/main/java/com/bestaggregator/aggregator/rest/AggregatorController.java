package com.bestaggregator.aggregator.rest;

import com.bestaggregator.aggregator.connectros.Connector;
import com.bestaggregator.aggregator.data.Place;
import com.bestaggregator.aggregator.entity.OrderTaxi;
import com.bestaggregator.aggregator.repo.OrderTaxiRepository;
import com.bestaggregator.aggregator.request.CancelRequest;
import com.bestaggregator.aggregator.request.CreateRequest;
import com.bestaggregator.aggregator.request.GetPricesRequest;
import com.bestaggregator.aggregator.response.CancelResponse;
import com.bestaggregator.aggregator.response.CreateResponse;
import com.bestaggregator.aggregator.response.GetPricesResponse;
import com.bestaggregator.aggregator.security.SecurityConfig;
import com.bestaggregator.aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class AggregatorController {

    @Autowired
    Map<String, Connector> map;

    @Autowired
    UserService userService;

    @Autowired
    OrderTaxiRepository orderRepository;

    @RequestMapping(value = "/order/getPrices",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetPricesResponse>> getPrices(@RequestBody GetPricesRequest request){
        if (!request.getStartPlace().inRange() || !request.getFinishPlace().inRange())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<GetPricesResponse> responses = new LinkedList<>();

        for (Connector c : map.values())
            responses.add(c.getPrices(request));

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/createOrder",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateResponse> createOrder(@RequestBody CreateRequest request){

        if (!request.getStartPlace().inRange() || !request.getFinishPlace().inRange())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (map.get(request.getAgr())==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        CreateResponse order = map.get(request.getAgr()).createOrder(request);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        order.setAgr(request.getAgr());

        orderRepository.save(
                new OrderTaxi(
                        1L, order.getOrder_id(),
                        order.getTransaction(), true,
                        userService.findByPhone(SecurityConfig.getCurrentUsername())));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/cancelOrder",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CancelResponse> cancelOrder(@RequestBody CancelRequest request){
        OrderTaxi o = orderRepository.findByUserAndId_order(
                userService.findByPhone(SecurityConfig.getCurrentUsername()).getId(),
                request.getOrder_id());
        if (o == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (o.getStatus() == false)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (map.get(request.getAgr())==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        CancelResponse order = map.get(request.getAgr()).cancelOrder(request);
        order.setAgr(request.getAgr());

        o.setStatus(false);
        orderRepository.save(o);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
