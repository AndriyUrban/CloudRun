package com.cpg.cloudrunprocessor.DAO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Entity {

    private Long id;

    private Long count;
}
