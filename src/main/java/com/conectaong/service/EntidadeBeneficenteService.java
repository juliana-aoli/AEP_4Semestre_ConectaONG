
package com.conectaong.service;

import com.conectaong.model.EntidadeBeneficente;
import com.conectaong.repository.EntidadeBeneficenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadeBeneficenteService {

    @Autowired
    private EntidadeBeneficenteRepository entidadeBeneficenteRepository;

    public EntidadeBeneficente salvar(EntidadeBeneficente entidade) {
        return entidadeBeneficenteRepository.save(entidade);
    }

    public List<EntidadeBeneficente> listarEntidades() {
        return entidadeBeneficenteRepository.findAll();
    }

    public Optional<EntidadeBeneficente> buscarPorId(Integer id) {
        return entidadeBeneficenteRepository.findById(id);
    }

    public Optional<EntidadeBeneficente> atualizarEntidade(Integer id, EntidadeBeneficente entidadeAtualizada) {
        return entidadeBeneficenteRepository.findById(id)
                .map(entidade -> {
                    entidade.setNomeFantasia(entidadeAtualizada.getNomeFantasia());
                    entidade.setCnpj(entidadeAtualizada.getCnpj());
                    entidade.setTelefone(entidadeAtualizada.getTelefone());
                    entidade.setEmail(entidadeAtualizada.getEmail());
                    entidade.setEnderecoCompleto(entidadeAtualizada.getEnderecoCompleto());
                    entidade.setNomeResponsavelTecnico(entidadeAtualizada.getNomeResponsavelTecnico());
                    return entidadeBeneficenteRepository.save(entidade);
                });
    }

    public boolean deletarEntidade(Integer id) {
        if (entidadeBeneficenteRepository.existsById(id)) {
            entidadeBeneficenteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

