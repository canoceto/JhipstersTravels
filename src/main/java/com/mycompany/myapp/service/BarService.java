package com.mycompany.myapp.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
public class BarService {

    private final Logger log = LoggerFactory.getLogger(BarService.class);

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            log.info("" + changeSede());
        };
    }

    public boolean changeSede() {
        boolean returnValue = false;

        return returnValue;
    }
}
