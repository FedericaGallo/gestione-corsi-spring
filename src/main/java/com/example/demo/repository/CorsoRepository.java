package com.example.demo.repository;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Integer> {
    Page<Corso> findAllByNomeCorso(String nomeCorso, Pageable pageable);
    Page<Corso> findAll(Pageable pageable);
}
