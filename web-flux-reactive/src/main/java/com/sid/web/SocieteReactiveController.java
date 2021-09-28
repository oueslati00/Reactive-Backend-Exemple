package com.sid.web;

import com.sid.Entites.societe;
import com.sid.dao.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SocieteReactiveController {
   @Autowired
    private SocieteRepository societeRepository;


   @GetMapping(value = "/societes")
   public Flux<societe> findAll(){
       return societeRepository.findAll();
   }
   //  if the societe does not exist nothing is returned
    @GetMapping(value = "/societes/{id}")
    public Mono<societe> getOne(@PathVariable String id){
        return societeRepository.findById(id);
    }
    @PostMapping("/societes")
    public Mono<societe> save(@RequestBody societe soc){
       return societeRepository.save(soc);
    }
    @DeleteMapping("/societes/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return societeRepository.deleteById(id);
    }
    @PutMapping("/societes/{id}")
    public Mono<societe> update(@RequestBody societe soc, @PathVariable String id){
       soc.setId(id);
        return societeRepository.save(soc);
    }

}
