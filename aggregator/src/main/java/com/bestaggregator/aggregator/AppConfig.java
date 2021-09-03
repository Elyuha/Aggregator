package com.bestaggregator.aggregator;

import com.bestaggregator.aggregator.connectros.Connector;
import com.bestaggregator.aggregator.connectros.impl.BestTaxiConnector;
import com.bestaggregator.aggregator.connectros.impl.TaxiLyohaConnector;
import com.bestaggregator.aggregator.connectros.impl.TaxiVasyaConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Logger logger(){
        Logger logger = Logger.getLogger("MyLog");
        try {
            logger.addHandler(new FileHandler("C:\\aggregator\\aggregator\\log.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("ssss");
        return logger;
    }

    @Bean
    public List<Connector> list(){
        List<Connector> list = new ArrayList<>();
        Collections.addAll(list,
                new BestTaxiConnector(),
                new TaxiLyohaConnector(),
                new TaxiVasyaConnector());
        return list;
    }
}
