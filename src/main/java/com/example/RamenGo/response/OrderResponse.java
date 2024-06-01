package com.example.RamenGo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {

    @Schema(example = "12345")
    private String id;

    @Schema(example = "Salt and Chasu Ramen")
    private String description;

    @Schema(example = "https://tech.redventures.com.br/icons/ramen/ramenChasu.png")
    private String image;
}
