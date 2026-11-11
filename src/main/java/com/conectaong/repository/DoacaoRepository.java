

package com.conectaong.repository;

import com.conectaong.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

    List<Doacao> findByDoador_Id(Integer idDoador);

    List<Doacao> findByEntidadeBeneficente_Id(Integer idEntidade);
}
