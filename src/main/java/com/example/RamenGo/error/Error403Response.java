package com.example.RamenGo.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Error403Response {
    @Schema(description = "Error message", example = "x-api-key header missing")
    private String errorMessage;
}
