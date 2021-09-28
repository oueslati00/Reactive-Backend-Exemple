package com.sid.web;

import com.sid.Entites.Transaction;
import com.sid.dao.SocieteRepository;
import com.sid.dao.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@RestController
public class StreamingData {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SocieteRepository societeRepository;

    private WebClient webclient;

    @GetMapping(value= "/streamTransactions" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> streamTransactions(){
        return transactionRepository.findAll();
    }
    // change to media type with application stream json value will display json data used for connection with webservice
    @GetMapping(value= "/streamTransactionsBySociete/{id}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> streamTransactionsBySociete(@PathVariable String id){
        return societeRepository.findById(id)
                .flatMapMany(soc ->{
                    // flatManyUsed when we recive data with Mono will be transfered to Flux
                    Flux<Long> interval = Flux.interval(Duration.ofMillis(1000));
                    // create flux with duration
                    Flux<Transaction> transactionFlux = Flux.fromStream(Stream.generate(()->{
                        Transaction transaction = new Transaction();
                        transaction.setInstant(Instant.now());
                        transaction.setSociete(soc);
                        transaction.setPrice(soc.getPrice() + ((Math.random()*12)-6)/100);
                        return transaction;
                    }));
                    // create flux with Transaction
                    return Flux
                            .zip(interval, transactionFlux)
                            .map(data ->{
                                return data.getT2();
                            });
                    // merge the two flux with zip and return the transaction with map
                });
    }

    @GetMapping(value = "/events/{id}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> events(@PathVariable String id){
        webclient = WebClient.create("http://localhost:8082");
        Flux<Event> ev =webclient.get()
                .uri("/streamEvent/" + id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Event.class);
        return ev ;
    }
}
