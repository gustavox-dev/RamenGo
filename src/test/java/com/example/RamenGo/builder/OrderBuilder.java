package com.example.RamenGo.builder;

import com.example.RamenGo.domain.Broth;
import com.example.RamenGo.domain.Proteins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderBuilder {

    @Autowired
    private ProteinBuild proteinBuild;

    @Autowired
    private BrothBuilder brothBuilder;

    public Proteins mockProtein(){
        return proteinBuild.mockProtein();
    }

    public Broth mockBroth(){
        return brothBuilder.mockBroth();
    }
}
