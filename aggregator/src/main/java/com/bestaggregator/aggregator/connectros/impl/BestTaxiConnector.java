package com.bestaggregator.aggregator.connectros.impl;

import com.bestaggregator.aggregator.connectros.Connector;
import com.bestaggregator.aggregator.request.CancelRequest;
import com.bestaggregator.aggregator.request.CreateRequest;
import com.bestaggregator.aggregator.request.GetPricesRequest;
import com.bestaggregator.aggregator.response.CancelResponse;
import com.bestaggregator.aggregator.response.CreateResponse;
import com.bestaggregator.aggregator.response.GetPricesResponse;
import com.bestaggregator.aggregator.security.SecurityConfig;
import com.bestaggregator.aggregator.service.UserService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Logger;

public class BestTaxiConnector implements Connector {
    @Autowired
    Logger logger;

    @Autowired
    UserService userService;

    @Override
    public CreateResponse createOrder(CreateRequest request) {
        try {
            StringBuilder postData = new StringBuilder();
            postData.append("id=" + request.getUser_id());
            postData.append("&");
            postData.append("name=" + request.getName());
            postData.append("&");
            postData.append("latitudeStart=" + request.getStartPlace().getLatitude());
            postData.append("&");
            postData.append("longitudeStart=" + request.getStartPlace().getLongitude());
            postData.append("&");
            postData.append("latitudeFinish=" + request.getFinishPlace().getLatitude());
            postData.append("&");
            postData.append("longitudeFinish=" + request.getFinishPlace().getLongitude());
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            String url = "http://localhost:8081/createOrder";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer answer = new StringBuffer();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    answer.append(inputLine);
                }
                bufferedReader.close();
                JSONObject object = new JSONObject(answer.toString());

                CreateResponse response = new CreateResponse();
                response.setOrder_id(object.getLong("order_id"));
                response.setPrice(object.getInt("cost"));
                response.setTransaction(object.getLong("transaction"));
                response.setModelA(object.getString("auto"));
                response.setNumberA(object.getString("number"));

                return response;
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (ProtocolException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (MalformedURLException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (IOException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        }

        return null;
    }

    @Override
    public CancelResponse cancelOrder(CancelRequest request) {
        try {
            StringBuilder postData = new StringBuilder();
            postData.append("order_id=" + request.getOrder_id());
            postData.append("&");
            postData.append("transaction=" + request.getTransaction());
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            String url = "http://localhost:8081/cancelOrder";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer answer = new StringBuffer();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    answer.append(inputLine);
                }
                bufferedReader.close();
                JSONObject object = new JSONObject(answer.toString());

                CancelResponse response = new CancelResponse();
                response.setOrder_id(object.getLong("order_id"));
                response.setTransaction(object.getLong("transaction"));

                return response;
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (ProtocolException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (MalformedURLException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (IOException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        }

        return null;
    }

    @Override
    public GetPricesResponse getPrices(GetPricesRequest request) {
        try {
            StringBuilder postData = new StringBuilder();
            postData.append("id=" + request.getId());
            postData.append("&");
            postData.append("latitudeStart=" + request.getStartPlace().getLatitude());
            postData.append("&");
            postData.append("longitudeStart=" + request.getStartPlace().getLongitude());
            postData.append("&");
            postData.append("latitudeFinish=" + request.getFinishPlace().getLatitude());
            postData.append("&");
            postData.append("longitudeFinish=" + request.getFinishPlace().getLongitude());
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            String url = "http://localhost:8081/getPrice";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer answer = new StringBuffer();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    answer.append(inputLine);
                }
                bufferedReader.close();
                JSONObject object = new JSONObject(answer.toString());

                GetPricesResponse response = new GetPricesResponse();
                response.setPrice(object.getInt("cost"));
                response.setStartPlace(request.getStartPlace());
                response.setFinishPlace(request.getFinishPlace());
                response.setAgr("BestTaxi");

                return response;
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (ProtocolException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (MalformedURLException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        } catch (IOException e) {
            logger.info(ExceptionUtils.getStackTrace(e) +
                    userService.findByPhone(SecurityConfig.getCurrentUsername()));
        }

        return null;
    }
}
