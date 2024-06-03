package com.example.RamenGo.controller;

import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.dto.ProteinsDTO;
import com.example.RamenGo.error.Error403Response;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.response.ProteinsResponse;
import com.example.RamenGo.service.ProteinsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProteinsController {

    @Autowired
    private ProteinsService service;

    @GetMapping("/proteins")
    @Operation(summary = "List all available proteins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of proteins"),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error403Response.class))),
    })
    public List<Proteins> findAll(@RequestHeader("x-api-key") String apiKey) throws UnauthorisedException {
        return service.findAll(apiKey);
    }

}
