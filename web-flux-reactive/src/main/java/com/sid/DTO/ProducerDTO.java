package com.sid.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ProducerData")
public class ProducerDTO {
    @JsonProperty("id")
    private String id;

    public ProducerDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FakeProducerDTO{" +
                "id='" + id + '\'' +
                '}';
    }

}
