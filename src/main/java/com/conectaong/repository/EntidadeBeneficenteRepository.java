
package com.conectaong.repository;

import com.conectaong.model.EntidadeBeneficente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeBeneficenteRepository extends JpaRepository<EntidadeBeneficente, Integer> {
}
