package com.cpg.cloudrunprocessor.service;

import com.cpg.cloudrunprocessor.DAO.ProductEntity;
import com.cpg.cloudrunprocessor.DAO.EntityDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceRepo {

    private static final Logger logger = LoggerFactory.getLogger(ServiceRepo.class);

    public static final String WARN_MESSAGE = "Not enough items in DB";

    private final EntityDAO dao;

    @Autowired
    public ServiceRepo(EntityDAO dao) {
        this.dao = dao;
    }

    public boolean getFromDB(ProductEntity body) {

        Long id = body.getId();
        Long countFromBody = body.getCount();

        Optional<ProductEntity> entityFromBD = dao.findById(id);

        boolean result = compare(countFromBody, entityFromBD.get().getCount());

        if (result) {
            updateEntity(id, countFromBody, entityFromBD.get().getCount());
            return true;
        } else {
            logger.warn(WARN_MESSAGE);
            return false;
        }

    }

    private boolean compare(Long countFromBody, Long countFromDB) {
        return countFromDB >= countFromBody;
    }

    private void updateEntity(Long id, Long countFromBody, Long countFromDB) {
        ProductEntity entity = ProductEntity.builder().id(id).count(countFromDB - countFromBody).build();
        dao.save(entity);
    }

}

