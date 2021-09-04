package com.bestaggregator.aggregator;

import com.bestaggregator.aggregator.connectros.impl.BestTaxiConnector;
import com.bestaggregator.aggregator.data.Place;
import com.bestaggregator.aggregator.request.CreateRequest;
import com.bestaggregator.aggregator.response.CreateResponse;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
public class AggregatorApplication {
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		SpringApplication.run(AggregatorApplication.class, args);
	}
}
