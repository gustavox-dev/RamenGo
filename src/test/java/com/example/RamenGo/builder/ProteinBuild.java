package com.example.RamenGo.builder;

import com.example.RamenGo.domain.Proteins;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProteinBuild {

    public List<Proteins> mockProteins(){
        return Lists.newArrayList(
                new Proteins(1L,
                        "https://tech.redventures.com.br/icons/pork/inactive.svg",
                        "https://tech.redventures.com.br/icons/pork/active.svg",
                        "Chasu",
                        "A sliced flavourful pork meat with a selection of season vegetables.",
                        10)
        );
    }

    public Proteins mockProtein(){
        return new Proteins(1L,
                "https://tech.redventures.com.br/icons/pork/inactive.svg",
                "https://tech.redventures.com.br/icons/pork/active.svg",
                "Chasu",
                "A sliced flavourful pork meat with a selection of season vegetables.",
                10);
    }
}
