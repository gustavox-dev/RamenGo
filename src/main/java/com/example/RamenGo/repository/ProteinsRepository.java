package com.example.RamenGo.repository;

import com.example.RamenGo.domain.Proteins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProteinsRepository extends JpaRepository<Proteins, Long> {
}
