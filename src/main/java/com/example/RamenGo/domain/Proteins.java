package com.example.RamenGo.domain;

import com.example.RamenGo.exceptions.MissingAttributeException;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_proteins")
@Data
@NoArgsConstructor
public class Proteins extends Preparation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Builder
    public Proteins(String imageInactive, String imageActive, String name, String description, Integer price) {
        super(imageInactive, imageActive, name, description, price);
    }


}
