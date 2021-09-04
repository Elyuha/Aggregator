package com.bestaggregator.aggregator;

import com.bestaggregator.aggregator.connectros.Connector;
import com.bestaggregator.aggregator.connectros.impl.BestTaxiConnector;
import com.bestaggregator.aggregator.connectros.impl.TaxiLyohaConnector;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Configuration
@EnableAutoConfiguration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Logger logger(){
        Logger logger = Logger.getLogger("MyLog");
        try {
            logger.addHandler(new FileHandler("C:\\Projects\\Aggregator\\aggregator\\log.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }

    @Bean
    public Map<String, Connector> map(){
        Map<String, Connector> map = new HashMap<>();
        map.put("BestTaxi", new BestTaxiConnector());
        map.put("TaxiLyoha", new TaxiLyohaConnector());

        return map;
    }
}
