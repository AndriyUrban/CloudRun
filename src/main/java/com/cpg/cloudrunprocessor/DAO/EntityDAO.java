package com.cpg.cloudrunprocessor.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EntityDAO {

    private final Search search;

    @Autowired
    public EntityDAO(Search search) {
        this.search = search;
    }

    public Optional<Entity> searchById(Long id){
        return search.findById(id);
    }

    public void update(Entity entity) {
        search.save(entity);
    }
}

