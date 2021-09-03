package com.bestaggregator.aggregator;

import lombok.Data;

@Data
public class Response{
    private Long id;
    private String mess;
    public static Response genRes(Long l, String s){
        Response r = new Response();
        r.setId(l);
        r.setMess(s);
        return r;
    }
}


