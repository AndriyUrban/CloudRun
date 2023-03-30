package com.cpg.cloudrunprocessor.web;

import com.cpg.cloudrunprocessor.dto.TestDTO;
import com.cpg.cloudrunprocessor.service.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebController {

    @Autowired
    private ServiceRepo serviceRepo;


    @PostMapping("/connect")
    public boolean endpoint (@RequestBody TestDTO requestBody) {
        System.out.println("here we are!!!");
        return serviceRepo.getFromDB(requestBody);

    }

    @GetMapping("/da")
    public void e (){
        System.out.println("444444444444444444444444");
    }
}

