package com.example.RamenGo.service;

import com.example.RamenGo.domain.Broth;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.repository.BrothsRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrothsService {

    private static final Logger LOG = LoggerFactory.getLogger(BrothsService.class);

    @Autowired
    private BrothsRepository repo;

    @Autowired
    private TokenService tokenService;

    public List<Broth> findAll(String apiKey) throws UnauthorisedException {
        try{
            tokenService.validateToken(apiKey);
            return repo.findAll();
        } catch (Exception e){
            LOG.error(" ===== ERROR ===== ", e);
            throw new UnauthorisedException();
        }
    }

    public Broth findBrothsById(Long id) throws InternalErrorException, ItemNotFoundException {
        try {
            return repo.findById(id).orElseThrow(() -> new ItemNotFoundException("Broth not found"));
        } catch (ItemNotFoundException e){
            throw new ItemNotFoundException("Broth not found");
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new InternalErrorException();
        }

    }

}
