package com.example.TaxiBest.rest;

import com.example.TaxiBest.ResAndReq.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ResponseCache;
import java.util.Random;

@RestController
public class Controller {

    @RequestMapping(value = "/getPrice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePrices> price(RequestPrices prices){
        ResponsePrices res = new ResponsePrices();
        res.setCost(cost (prices.getLatitudeStart(),
                prices.getLongitudeStart(),
                prices.getLatitudeFinish(),
                prices.getLongitudeFinish()));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseCreate> create(RequestCreate create){
        ResponseCreate responseCreate = new ResponseCreate();
        responseCreate.setOrder_id((long) (1 + (Math.random() * 100000)));
        responseCreate.setTransaction((long) (1 + (Math.random() * 100000)));
        responseCreate.setCost(cost(create.getLatitudeStart(),
                create.getLongitudeStart(),
                create.getLatitudeFinish(),
                create.getLongitudeFinish()));
        responseCreate.setNumber("a111aa");
        responseCreate.setAuto("AAAAA");
        System.out.println(responseCreate);
        return new ResponseEntity<>(responseCreate, HttpStatus.OK);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseCancel> cancel(RequestCancel cancel){

        ResponseCancel response = new ResponseCancel();
        response.setOrder_id(cancel.getOrder_id());
        response.setTransaction(cancel.getTransaction());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static int cost(Double latitudeStart, Double longitudeStart,
                       Double latitudeFinish, Double longitudeFinish){
        return (int) ((latitudeStart * longitudeStart)
                        /(latitudeFinish + longitudeFinish));
    }
}
