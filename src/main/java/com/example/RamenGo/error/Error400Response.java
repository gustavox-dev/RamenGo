package com.example.RamenGo.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Error400Response {
    @Schema(description = "Error message", example = "both brothId and proteinId are required")
    private String errorMessage;


}
