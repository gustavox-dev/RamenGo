package com.example.RamenGo.domain;

import com.example.RamenGo.dto.ProteinsDTO;
import com.example.RamenGo.exceptions.MissingAttributeException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Preparation {

    @Schema(example = "https://tech.redventures.com.br/icons/salt/inactive.svg")
    @Column(name = "image_inactive", nullable = false)
    private String imageInactive;

    @Schema(example = "https://tech.redventures.com.br/icons/salt/active.svg")
    @Column(name = "image_active", nullable = false)
    private String imageActive;

    @Schema(example = "Salt")
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(example = "Simple like the seawater, nothing more")
    @Column(name = "description", nullable = false)
    private String description;

    @Schema(example = "10")
    @Column(name = "price", nullable = false)
    private Integer price;

    public Preparation(String imageInactive, String imageActive, String name, String description, Integer price) {
        this.imageInactive = imageInactive;
        this.imageActive = imageActive;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void validateProteins(Preparation preparation) throws MissingAttributeException {
        if(preparation.getImageInactive() == null || preparation.getImageInactive().isEmpty()){
            throw new MissingAttributeException("imageInactive");
        }
        if(preparation.getImageActive() == null || preparation.getImageActive().isEmpty()){
            throw new MissingAttributeException("imageActive");
        }
        if(preparation.getName() == null || preparation.getName().isEmpty()){
            throw new MissingAttributeException("name");
        }
        if(preparation.getDescription() == null || preparation.getDescription().isEmpty()){
            throw new MissingAttributeException("description");
        }
        if(preparation.getPrice() == null){
            throw new MissingAttributeException("price");
        }
    }
}
