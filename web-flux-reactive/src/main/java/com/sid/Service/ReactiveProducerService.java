package com.sid.Service;


import com.sid.DTO.ProducerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReactiveProducerService {
    private final Logger log = LoggerFactory.getLogger(ReactiveProducerService.class);
    private final ReactiveKafkaProducerTemplate<String, ProducerDTO> reactiveKafkaProducerTemplate;

    @Value(value = "${PRODUCER_DTO_TOPIC}")
    private String topic;

    public ReactiveProducerService(ReactiveKafkaProducerTemplate<String, ProducerDTO> reactiveKafkaProducerTemplate) {
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
    }
    public void send(ProducerDTO producerDTO) {
        log.info("send to topic={}, {}={},", topic, ProducerDTO.class.getSimpleName(), producerDTO);
        reactiveKafkaProducerTemplate.send(topic, producerDTO)
               .doOnSuccess(senderResult -> log.info("sent {} offset : {}", producerDTO, senderResult.recordMetadata().offset()))
                .subscribe(
                        data ->{
                            System.out.println(data);
                        }
                );
    }
}
