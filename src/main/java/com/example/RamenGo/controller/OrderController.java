package com.example.RamenGo.controller;

import com.example.RamenGo.domain.Order;
import com.example.RamenGo.error.Error400Response;
import com.example.RamenGo.error.Error403Response;
import com.example.RamenGo.error.Error500Response;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.response.OrderResponse;
import com.example.RamenGo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/order")
    public List<Order> findAll(@RequestHeader("x-api-key") String apiKey) throws InternalErrorException {
        return service.findAll();
    }

    @PostMapping(value = "/order")
    @Operation(summary = "Place an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order placed successfully",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error400Response.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error403Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error500Response.class)))
    })
    public OrderResponse createBroths(@RequestHeader("x-api-key") String apiKey, @RequestBody String order) throws IOException, ItemNotFoundException, UnauthorisedException, InterruptedException, InternalErrorException {
        return service.createOrder(apiKey, order);
    }

}
