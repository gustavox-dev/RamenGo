package com.example.RamenGo.domain;

import com.example.RamenGo.exceptions.MissingAttributeException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_proteins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proteins extends Preparation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "1")
    private Long id;

    @Builder
    public Proteins(Long id, String imageInactive, String imageActive, String name, String description, Integer price) {
        super(imageInactive, imageActive, name, description, price);
        this.id = id;
    }

}
