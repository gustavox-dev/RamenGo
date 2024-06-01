package com.example.RamenGo.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Error500Response {
    @Schema(description = "Error message", example = "could not place order")
    private String errorMessage;
}
