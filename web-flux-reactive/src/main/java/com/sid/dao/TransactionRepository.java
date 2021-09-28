package com.sid.dao;

import com.sid.Entites.Transaction;
import com.sid.Entites.societe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction,String> {

    // TODO : create this method
    Flux<Transaction> findBysociete(societe societeMono);
}
