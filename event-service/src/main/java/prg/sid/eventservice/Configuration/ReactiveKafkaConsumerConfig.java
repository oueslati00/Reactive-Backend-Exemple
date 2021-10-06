package prg.sid.eventservice.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import prg.sid.eventservice.DTO.ConsumerDTO;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;

@Configuration
public class ReactiveKafkaConsumerConfig {
    @Bean
    public ReceiverOptions<String, ConsumerDTO> kafkaReceiverOptions(@Value(value = "${TOPIC}") String topic, KafkaProperties kafkaProperties) {
        ReceiverOptions<String, ConsumerDTO> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
        return basicReceiverOptions.subscription(Collections.singletonList(topic));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, ConsumerDTO> reactiveKafkaConsumerTemplate(ReceiverOptions<String, ConsumerDTO> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
    }
}
