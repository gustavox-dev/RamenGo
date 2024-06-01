package com.example.RamenGo.service;

import com.example.RamenGo.adapters.ExternalClientAdapter;
import com.example.RamenGo.adapters.OrderDTOAdapter;
import com.example.RamenGo.domain.Broths;
import com.example.RamenGo.domain.Order;
import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.dto.OrderDTO;
import com.example.RamenGo.exceptions.IdsMissingException;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.repository.OrderRepository;
import com.example.RamenGo.response.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository repo;

    @Autowired
    private ProteinsService proteinsService;

    @Autowired
    private BrothsService brothsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OrderDTOAdapter orderDTOAdapter;

    @Autowired
    private ExternalClientAdapter externalClientAdapter;

    public OrderResponse createOrder(String apiKey, String order) throws ItemNotFoundException, UnauthorisedException, IOException, InterruptedException, InternalErrorException {

        try {
            OrderDTO orderDTO = orderDTOAdapter.convertOrderDTOToText(order);
            if(orderDTO.brothId() == null || orderDTO.proteinId() == null){
                throw new IdsMissingException();
            }

            tokenService.validateToken(apiKey);

            Proteins proteins = proteinsService.findProteinById(orderDTO.brothId());
            Broths broths = brothsService.findBrothsById(orderDTO.proteinId());

            String description = broths.getName() + " and " + proteins.getName() + " Ramen";
            String image = "https://tech.redventures.com.br/icons/ramen/ramenChasu.png";

            Order newOrder = Order.builder()
                    .proteinId(proteins.getId())
                    .brothId(broths.getId())
                    .build();

            String orderId = externalClientAdapter.fetchDataFromExternalApi(apiKey);

            saveOrder(newOrder);
            return new OrderResponse(orderId, description, image);
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new InternalErrorException();
        }
    }

    public List<Order> findAll() throws InternalErrorException {
        try{
            return repo.findAll();
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new InternalErrorException();
        }

    }

    private void saveOrder(Order order){
        repo.save(order);
    }
}
