package com.sid.web;

import com.sid.DTO.ProducerDTO;
import com.sid.Service.ReactiveProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
public class KafkaController {

    @Autowired
    private ReactiveProducerService reactiveProducerService;
    @GetMapping(value = "/api/kafka/{data}")
    public ResponseEntity<String> senKafkaMessage(@PathVariable String data) throws InterruptedException {
        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        Disposable test = reactiveProducerService.send(new ProducerDTO(data));
        latch.await(5, TimeUnit.SECONDS);
        if(test.isDisposed()){
            return new ResponseEntity<>("message send with sucess", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("message fail !! sorry", HttpStatus.BAD_REQUEST);
        }


    }
}
