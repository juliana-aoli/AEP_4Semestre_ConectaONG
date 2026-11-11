
package com.conectaong.service;

import com.conectaong.model.Doacao;
import com.conectaong.model.StatusDoacao;
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

        if (doacao.getStatus() == null || doacao.getStatus().isBlank()) {
            doacao.setStatus(StatusDoacao.statusInicial());
        } else if (!StatusDoacao.ehValido(doacao.getStatus())) {
            throw new IllegalArgumentException(
                    "Status inválido. Informe um dos seis status permitidos."
            );
        }

        return doacaoRepository.save(doacao);
    }

    public List<Doacao> listarDoacoes() {
        return doacaoRepository.findAll();
    }

    // Consulta o historico de doacoes por doador
    public List<Doacao> listarPorDoador(Integer idDoador) {
        return doacaoRepository.findByDoador_Id(idDoador);
    }

    // Consulta o historico de doacoes por entidade beneficente
    public List<Doacao> listarPorEntidade(Integer idEntidade) {
        return doacaoRepository.findByEntidadeBeneficente_Id(idEntidade);
    }

    public Optional<Doacao> buscarPorId(Integer id) {
        return doacaoRepository.findById(id);
    }

    public Optional<Doacao> atualizarDoacao(
            Integer id, Doacao doacaoAtualizada) {

        if (doacaoAtualizada.getStatus() != null
                && !doacaoAtualizada.getStatus().isBlank()
                && !StatusDoacao.ehValido(doacaoAtualizada.getStatus())) {

            throw new IllegalArgumentException(
                    "Status inválido. Informe um dos seis status permitidos."
            );
        }

        return doacaoRepository.findById(id)
                .map(doacao -> {

                    doacao.setDataDoacao(doacaoAtualizada.getDataDoacao());

                    if (doacaoAtualizada.getStatus() != null
                            && !doacaoAtualizada.getStatus().isBlank()) {
                        doacao.setStatus(doacaoAtualizada.getStatus());
                    }

                    doacao.setEntidadeBeneficente(
                            doacaoAtualizada.getEntidadeBeneficente()
                    );

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
