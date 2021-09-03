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
//	public static void test() throws IOException, ParserConfigurationException, SAXException {
//		String url = "http://localhost:8081/createOrder";
//		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//		connection.setUseCaches(false);
//		connection.setRequestMethod("GET");
//
//		System.out.println(connection.getResponseCode());
//
//		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
//			BufferedReader bufferedReader = new BufferedReader(
//					new InputStreamReader(connection.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while ((inputLine = bufferedReader.readLine()) != null) {
//				response.append(inputLine);
//			}
//			bufferedReader.close();
//			JSONObject object = new JSONObject(response.toString());
//			System.out.println("id: " + object.getInt("id"));
//			System.out.println("mess: " + object.getString("mess"));
//		}
//
//	}
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
//		test();
//		test2();
//		test3();
		SpringApplication.run(AggregatorApplication.class, args);
	}

	private static void test3() {
		BestTaxiConnector connector = new BestTaxiConnector();
		CreateRequest request = new CreateRequest();
		request.setUser_id(12L);
		request.setName("adsada");
		request.setStartPlace(new Place(12d, 13d));
		request.setFinishPlace(new Place(14d, 22d));
		request.setDate(LocalDate.now());
		request.setTime(LocalTime.now());
		CreateResponse response = connector.createOrder(request);
		System.out.println(response);
	}

//	private static void test2() throws IOException {
//		Response genRes = Response.genRes(1L, "privet");
//		StringBuilder postData = new StringBuilder();
//		postData.append("id="+genRes.getId());
//		postData.append("&");
//		postData.append("mess="+genRes.getMess());
//		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//		String url = "http://localhost:8081/test";
//		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//		connection.setRequestMethod("POST");
//		connection.setDoOutput(true);
//		connection.getOutputStream().write(postDataBytes);
//		System.out.println();
//		System.out.println(connection.getResponseCode());
//		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
//			BufferedReader bufferedReader = new BufferedReader(
//					new InputStreamReader(connection.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while ((inputLine = bufferedReader.readLine()) != null) {
//				response.append(inputLine);
//			}
//			bufferedReader.close();
//			JSONObject object = new JSONObject(response.toString());
//			System.out.println("id: " + object.getInt("id"));
//			System.out.println("mess: " + object.getString("mess"));
//		}
//
//	}

}
