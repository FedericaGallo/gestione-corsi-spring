package com.example.demo.repository;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorsoRepository extends JpaRepository<Corso, Integer> {
}
