package com.sid.web;

import com.sid.Entites.Transaction;
import com.sid.Entites.societe;
import com.sid.dao.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class TransactionReactiveController {

   @Autowired
    private TransactionRepository transactionRepository;

   @GetMapping(value = "/transaction")
   public Flux<Transaction> findAll(){
       return transactionRepository.findAll();
   }

   //  if the Transaction does not exist nothing is returned
    @GetMapping(value = "/transaction/{id}")
    public Mono<Transaction> getOne(@PathVariable String id){
        return transactionRepository.findById(id);
    }

    // TODO : this method does not work
    @GetMapping(value = "/transactionByCompany/{id}")
    public Flux<Transaction> getTransactionByCompany(@PathVariable String id){
       societe societe = new societe(); societe.setId(id);
       //Mono<societe> societeMono = societeRepository.findById(id); : this solution does not work
       return transactionRepository.findBysociete(societe);
    }

    @PostMapping("/transaction")
    public Mono<Transaction> save(@RequestBody Transaction soc){
       return transactionRepository.save(soc);
    }

    @DeleteMapping("/transaction/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return transactionRepository.deleteById(id);
    }

    @PutMapping("/transaction/{id}")
    public Mono<Transaction> update(@RequestBody Transaction soc, @PathVariable String id){
       soc.setId(id);
        return transactionRepository.save(soc);
    }
}
