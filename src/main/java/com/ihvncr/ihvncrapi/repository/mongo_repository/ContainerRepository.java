package com.ihvncr.ihvncrapi.repository.mongo_repository;


import com.ihvncr.ihvncrapi.mongo.model.Container;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContainerRepository extends MongoRepository<Container,String> {
    Container findByMessageDataPatientIdentifiersIdentifier(String identifier);

}
