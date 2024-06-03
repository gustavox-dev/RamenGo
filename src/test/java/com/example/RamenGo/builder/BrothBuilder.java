package com.example.RamenGo.builder;

import com.example.RamenGo.domain.Broth;
import com.example.RamenGo.repository.BrothsRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrothBuilder {

    @Autowired
    private BrothsRepository repository;

    public List<Broth> mockBrothList(){
        return Lists.newArrayList(
                new Broth(1L,
                        "https://tech.redventures.com.br/icons/pork/inactive.svg",
                        "https://tech.redventures.com.br/icons/pork/active.svg",
                        "Chasu",
                        "A sliced flavourful pork meat with a selection of season vegetables.",
                        10)
        );
    }

    public Broth mockBroth(){
        return new Broth(1L,
                "https://tech.redventures.com.br/icons/pork/inactive.svg",
                "https://tech.redventures.com.br/icons/pork/active.svg",
                "Chasu",
                "A sliced flavourful pork meat with a selection of season vegetables.",
                10);
    }
}
