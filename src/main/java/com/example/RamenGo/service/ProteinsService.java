package com.example.RamenGo.service;

import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.repository.ProteinsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProteinsService {

    private static final Logger LOG = LoggerFactory.getLogger(ProteinsService.class);

    @Autowired
    private ProteinsRepository repo;

    @Autowired
    private TokenService tokenService;

    public List<Proteins> findAll(String apiKey) throws UnauthorisedException {
        try{
            tokenService.validateToken(apiKey);
            return repo.findAll();
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new UnauthorisedException();
        }
    }

    public Proteins findProteinById(Long id) throws ItemNotFoundException, InternalErrorException {
        try{
            return repo.findById(id).orElseThrow(() -> new ItemNotFoundException("Protein not found"));
        }catch (ItemNotFoundException e){
            throw new ItemNotFoundException("Broth not found");
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new InternalErrorException();
        }

    }
}
