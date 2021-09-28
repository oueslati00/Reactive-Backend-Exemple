package com.sid.dao;

import com.sid.Entites.societe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SocieteRepository extends ReactiveMongoRepository<societe,String> {
}
