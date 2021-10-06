package com.sid;

import com.sid.Entites.Transaction;
import com.sid.Entites.societe;
import com.sid.dao.SocieteRepository;
import com.sid.dao.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.stream.Stream;

@SpringBootApplication
public class WebFluxReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxReactiveApplication.class, args);
    }

    // after every connection , remove all the data , and add it again for test : solution for test
    //TODO : another solution will be added
    @Bean
    CommandLineRunner start(SocieteRepository societeRepository,
                            TransactionRepository transactionRepository){
        return args ->{
            societeRepository.deleteAll().subscribe(null,null, ()->{
                Stream.of("SG","AWB","BMCE","AXA").forEach(s->{
                    societeRepository.save(new societe(s,s,100 +Math.random() * 900))
                            .subscribe( soc -> {
                                System.out.println(soc);
                            });
                           transactionRepository.deleteAll().subscribe(null,null,()->{
                               societeRepository.findById(s).subscribe(soc -> {
                                   for (int i = 0; i <10 ; i++) {
                                       Transaction transaction = new Transaction();
                                       transaction.setInstant(Instant.now());
                                       transaction.setSociete(soc);
                                       transaction.setPrice(soc.getPrice() + ((Math.random()*12)-6)/100);
                                       transactionRepository.save(transaction).subscribe(System.out::println);
                                   }
                               });
                           });
                });
            });

        };
    }

}
