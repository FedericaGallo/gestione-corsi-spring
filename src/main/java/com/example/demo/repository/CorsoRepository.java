package com.example.demo.repository;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Integer> {
    Page<Corso> findAllByNomeCorso(String nomeCorso, Pageable pageable);
    Page<Corso> findAll(Pageable pageable);
    @Query(value = "SELECT c.* FROM corsotest c WHERE c.id NOT IN " +
            "(SELECT dc.corso_id FROM corso_discente dc WHERE dc.discente_id = :discenteId)",
            nativeQuery = true)
    List<Corso> findCorsiDisponibiliByDiscenteId(@Param("discenteId") Integer discenteId);

}
