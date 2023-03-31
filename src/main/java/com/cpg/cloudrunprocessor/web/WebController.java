package com.cpg.cloudrunprocessor.web;

import com.cpg.cloudrunprocessor.DAO.ProductEntity;
import com.cpg.cloudrunprocessor.service.ServiceRepo;
import com.cpg.cloudrunprocessor.service.PubSubService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    private static final String ENDPOINT_WAS_SUCCESSFULLY_REACHED = "Endpoint was successfully reached";

    private static final String HEALTHY = "Healthy";

    private final ServiceRepo service;

    private final PubSubService pubSub;

    @Autowired
    public WebController(ServiceRepo service, PubSubService pubSub) {
        this.service = service;
        this.pubSub = pubSub;
    }

    @SneakyThrows
    @PostMapping("/connect")
    public ResponseEntity<HttpStatus> endpoint (@RequestBody ProductEntity requestBody) {
        logger.info(ENDPOINT_WAS_SUCCESSFULLY_REACHED);
        if (service.getFromDB(requestBody)) {
            pubSub.publishMessage("OK");
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/alive")
    @ResponseBody
    public String healthCheck() {
        logger.info(ENDPOINT_WAS_SUCCESSFULLY_REACHED);
        return HEALTHY;
    }
}

