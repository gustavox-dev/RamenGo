package com.example.RamenGo.service;

import com.example.RamenGo.adapters.ExternalClientAdapter;
import com.example.RamenGo.adapters.OrderDTOAdapter;
import com.example.RamenGo.domain.Broth;
import com.example.RamenGo.domain.Order;
import com.example.RamenGo.domain.OrderIdResponse;
import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.dto.OrderDTO;
import com.example.RamenGo.exceptions.IdsMissingException;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.repository.OrderRepository;
import com.example.RamenGo.response.OrderResponse;
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

    public OrderResponse createOrder(String apiKey, String order) throws InternalErrorException {

        try {
            OrderDTO orderDTO = orderDTOAdapter.convertOrderDTOToText(order);
            if(orderDTO.brothId() == null || orderDTO.proteinId() == null){
                throw new IdsMissingException();
            }

            tokenService.validateToken(apiKey);

            Proteins proteins = proteinsService.findProteinById(orderDTO.brothId());
            Broth broth = brothsService.findBrothsById(orderDTO.proteinId());

            String description = broth.getName() + " and " + proteins.getName() + " Ramen";
            String image = "https://tech.redventures.com.br/icons/ramen/ramenChasu.png";
            String orderId = externalClientAdapter.fetchDataFromExternalApi(apiKey);

            Order newOrder = Order.builder()
                    .id(orderId)
                    .proteinId(proteins.getId())
                    .brothId(broth.getId())
                    .build();



            saveOrder(newOrder);
            return new OrderResponse(orderId, description, image);
        } catch (IdsMissingException e){
            LOG.error("======== ERROR ======== {}", e.getMessage());
            throw new IdsMissingException();
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new InternalErrorException();
        }
    }

    public List<Order> findAll(String apiKey) throws InternalErrorException {
        try{
            tokenService.validateToken(apiKey);
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
