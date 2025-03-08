package com.example.demo.repository;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, Integer> {
    Page<Discente> findAllByNome(String nome, Pageable pageable);
    Page<Discente> findAll(Pageable pageable);
    @Query(value = "SELECT d.* FROM discentetest d WHERE d.id NOT IN " +
            "(SELECT dc.discente_id FROM corso_discente dc WHERE dc.corso_id = :corsoId)",
            nativeQuery = true)
    List<Discente> findDiscentiDisponibiliByCorsoId(@Param("corsoId") Integer corsoId);
}
