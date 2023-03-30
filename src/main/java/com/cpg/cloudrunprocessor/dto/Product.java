package com.cpg.cloudrunprocessor.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    @Valid
    private int id;
    @Valid
    private String name;
    @Valid
    private double price;
}
