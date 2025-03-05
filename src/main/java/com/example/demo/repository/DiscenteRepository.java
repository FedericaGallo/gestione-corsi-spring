package com.example.demo.repository;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, Integer> {
    Page<Discente> findAllByNome(String nome, Pageable pageable);
    Page<Discente> findAll(Pageable pageable);
}
