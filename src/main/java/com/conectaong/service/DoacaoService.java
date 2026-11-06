package com.conectaong.service;

import com.conectaong.model.Doacao;
import com.conectaong.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    public Doacao salvar(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }

    public List<Doacao> listarDoacoes() {
        return doacaoRepository.findAll();
    }

    public Optional<Doacao> buscarPorId(Integer id) {
        return doacaoRepository.findById(id);
    }

    public Optional<Doacao> atualizarDoacao(Integer id, Doacao doacaoAtualizada) {
        return doacaoRepository.findById(id)
                .map(doacao -> {
                    doacao.setDataDoacao(doacaoAtualizada.getDataDoacao());
                    doacao.setStatus(doacaoAtualizada.getStatus());
                    doacao.setEntidadeBeneficente(doacaoAtualizada.getEntidadeBeneficente());
                    return doacaoRepository.save(doacao);
                });
    }

    public boolean deletarDoacao(Integer id) {
        if (doacaoRepository.existsById(id)) {
            doacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}