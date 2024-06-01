package com.example.RamenGo.service;

import com.example.RamenGo.domain.Broths;
import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.MissingAttributeException;
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

    public List<Broths> findAll(String apiKey) throws UnauthorisedException {
        try{
            tokenService.validateToken(apiKey);
            return repo.findAll();
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new UnauthorisedException();
        }
    }

    public Broths createBroths(Broths broths) throws InternalErrorException {

        try{
            Broths newBroths = Broths.builder()
                    .imageInactive(broths.getImageInactive())
                    .imageActive(broths.getImageActive())
                    .name(broths.getName())
                    .description(broths.getDescription())
                    .price(broths.getPrice())
                    .build();

            newBroths.validateProteins(newBroths);
            repo.save(newBroths);

            return newBroths;
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new InternalErrorException();
        }

    }

    public Broths findBrothsById(Long id) throws InternalErrorException {
        try {
            return repo.findById(id).orElseThrow(() -> new ItemNotFoundException("Broth not found"));
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new InternalErrorException();
        }

    }

    public void saveBroth(Broths broths){
        repo.save(broths);
    }
}
