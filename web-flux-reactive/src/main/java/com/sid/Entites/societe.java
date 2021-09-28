package com.sid.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data@AllArgsConstructor@NoArgsConstructor@ToString
public class societe {
    @Id
    private String id;
    private String name;
    private double price;
}
