package com.cpg.cloudrunprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class Product {
    private int id;
    @NonNull
    private String name;
    private double price;
}
