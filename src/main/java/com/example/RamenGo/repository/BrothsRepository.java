package com.example.RamenGo.repository;

import com.example.RamenGo.domain.Broth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrothsRepository extends JpaRepository<Broth, Long> {
}
