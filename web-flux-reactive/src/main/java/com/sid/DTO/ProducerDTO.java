package com.sid.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@JsonRootName("ProducerData")
@Data
@AllArgsConstructor
@ToString
public class ProducerDTO {
    @JsonProperty("id")
    private String id;



}
