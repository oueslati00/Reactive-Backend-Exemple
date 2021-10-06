package com.sid.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDTO {
    private Instant instant;
    private double value;
    private String societeID;
}
