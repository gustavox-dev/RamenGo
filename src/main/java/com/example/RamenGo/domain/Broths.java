package com.example.RamenGo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_broths")
@Data
@NoArgsConstructor
public class Broths extends Preparation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "1")
    private Long id;

    @Builder
    public Broths(String imageInactive, String imageActive, String name, String description, Integer price) {
        super(imageInactive, imageActive, name, description, price);
    }


}
