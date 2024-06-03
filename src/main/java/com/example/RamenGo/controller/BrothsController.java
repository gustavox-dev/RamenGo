package com.example.RamenGo.controller;

import com.example.RamenGo.domain.Broth;
import com.example.RamenGo.error.Error403Response;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.service.BrothsService;
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
public class BrothsController {

    @Autowired
    private BrothsService service;

    @GetMapping("/broths")
    @Operation(summary = "List all available broths")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of broths"),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error403Response.class))),
    })
    public List<Broth> findAll(@RequestHeader("x-api-key") String apiKey) throws UnauthorisedException {
        return service.findAll(apiKey);
    }


}
