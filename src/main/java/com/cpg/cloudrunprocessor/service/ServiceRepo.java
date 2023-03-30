package com.cpg.cloudrunprocessor.service;

import com.cpg.cloudrunprocessor.DAO.Entity;
import com.cpg.cloudrunprocessor.DAO.EntityDAO;
import com.cpg.cloudrunprocessor.dto.TestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceRepo {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRepo.class);

    private final EntityDAO dao;

    @Autowired
    public ServiceRepo(EntityDAO dao) {
        this.dao = dao;
    }

    public boolean getFromDB(TestDTO body) {
        //parse body
        //get num of required items
        //execute call to db
        //compare two numbers
        //if ok -> push "ok" message to pub sub
        //if not ok -> push "message not good"
        //remove from db
        Long id = body.getId();
        Long countFromBody = body.getCount();
        String name = body.getName();

        Optional<Entity> entityFromBD = find(id);

        boolean result = compare(countFromBody, entityFromBD.get().getCount());

        if (result) {
            updateEntity(id, countFromBody, entityFromBD.get().getCount());
            return true;
        } else {
            logger.warn("ERRRROOOR not enough items ");
            return false;
        }

    }

    private Optional<Entity> find(Long id) {
        return dao.searchById(id);
    }

    private boolean compare(Long countFromBody, Long countFromDB) {
        return countFromDB >= countFromBody;
    }

    private void updateEntity(Long id, Long countFromBody, Long countFromDB) {
        Entity entity = Entity.builder().id(id).count(countFromDB - countFromBody).build();
        dao.update(entity);
    }

    private boolean sentToMessageBroker(){
        return true;
    }
}

