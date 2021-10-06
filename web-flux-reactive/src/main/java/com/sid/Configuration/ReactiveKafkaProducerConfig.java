package com.sid.Configuration;

import com.sid.DTO.ProducerDTO;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

import java.util.List;
import java.util.Map;

@Configuration
public class ReactiveKafkaProducerConfig {
    @Bean
    public ReactiveKafkaProducerTemplate<String, ProducerDTO> reactiveKafkaProducerTemplate(KafkaProperties properties){
        Map<String, Object> props = properties.buildProducerProperties();
        return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(props));
    }
}
