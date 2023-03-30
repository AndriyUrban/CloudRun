package com.cpg.cloudrunprocessor.web;

import com.cpg.cloudrunprocessor.DAO.ProductEntity;
import com.cpg.cloudrunprocessor.service.ServiceRepo;
import com.cpg.cloudrunprocessor.service.PubSubService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebController {


    private final ServiceRepo serviceRepo;
    private final PubSubService pubSub;

    @Autowired
    public WebController(ServiceRepo serviceRepo, PubSubService pubSub) {
        this.serviceRepo = serviceRepo;
        this.pubSub = pubSub;
    }

    @SneakyThrows
    @PostMapping("/connect")
    public ResponseEntity<HttpStatus> endpoint (@RequestBody ProductEntity requestBody) {
        System.out.println("here we are!!!");
        if (serviceRepo.getFromDB(requestBody)) {
            pubSub.publishMessage("OK");
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }









//    @GetMapping("/da")
//    public void e (){
//        System.out.println("444444444444444444444444");
//    }
}

