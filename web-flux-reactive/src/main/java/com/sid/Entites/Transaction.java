package com.sid.Entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document
@AllArgsConstructor@Data@NoArgsConstructor@ToString
public class Transaction {
    private String id;
    private Instant instant;
    private double price;
    // json property annotation added to the case when we read Transaction Obj , eliminate the display of societe information
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private societe societe;
}
