package com.sid.web;

import com.sid.DTO.ProducerDTO;
import com.sid.Service.ReactiveProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestKafkaController {

    @Autowired
    private ReactiveProducerService reactiveProducerService;

    @GetMapping(value = "/api/kafka/{data}")
    public void senKafkaMessage(@PathVariable String data){
        reactiveProducerService.send(new ProducerDTO(data));
        return ;
    }

}
