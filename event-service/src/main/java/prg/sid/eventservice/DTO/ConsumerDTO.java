package prg.sid.eventservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("FakeConsumer")
public class ConsumerDTO {
    @JsonProperty("id")
    private String id;

    public ConsumerDTO(String id) {
        this.id = id;
    }
    public ConsumerDTO(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FakeConsumerDTO{" +
                "id='" + id + '\'' +
                '}';
    }
}
