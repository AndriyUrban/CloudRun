package com.cpg.cloudrunprocessor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {

    private static final Logger logger = LoggerFactory.getLogger(Validator.class);

    public boolean validateJson(String jsonBody) {
        logger.info("Hello");
        return true;
    }
}
