package com.cpg.cloudrunprocessor.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Search extends JpaRepository<Entity, Long> {

    Entity findById(long id);

    void update(Entity entity);

}

