package com.example.RamenGo.service;

import com.example.RamenGo.domain.Proteins;
import com.example.RamenGo.dto.ProteinsDTO;
import com.example.RamenGo.exceptions.InternalErrorException;
import com.example.RamenGo.exceptions.ItemNotFoundException;
import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.repository.ProteinsRepository;
import com.example.RamenGo.response.ProteinsResponse;
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

    public ProteinsResponse createProteins(ProteinsDTO proteins) throws InternalErrorException {

        try{
            Proteins newProteins = Proteins.builder()
                    .imageInactive(proteins.imageInactive())
                    .imageActive(proteins.imageActive())
                    .name(proteins.name())
                    .description(proteins.description())
                    .price(proteins.price())
                    .build();
            newProteins.validateProteins(newProteins);
            saveProtein(newProteins);

            return new ProteinsResponse(newProteins.getImageInactive(), newProteins.getImageActive(), newProteins.getName(), newProteins.getDescription(), newProteins.getPrice());

        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new InternalErrorException();
        }
    }

    public List<Proteins> findAll() throws UnauthorisedException {
        try{
            return repo.findAll();
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw new UnauthorisedException();
        }
    }

    public Proteins findProteinById(Long id) throws ItemNotFoundException {
        return repo.findById(id).orElseThrow(() -> new ItemNotFoundException("Protein not found"));
    }

    private void saveProtein(Proteins proteins){
        repo.save(proteins);
    }


}
