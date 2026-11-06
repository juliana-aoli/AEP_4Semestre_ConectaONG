package com.conectaong.service;

import com.conectaong.model.Doacao;
import com.conectaong.model.Doador;
import com.conectaong.repository.DoacaoRepository;
import com.conectaong.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoadorService {

    @Autowired
    private DoadorRepository doadorRepository;

    public Doador salvar(Doador doador){
        return doadorRepository.save(doador);
    }

    public List<Doador> listarDoadores(){
        return doadorRepository.findAll();
    }

    public Optional<Doador> buscarPorId(Integer id){
        return doadorRepository.findById(id);
    }

    public Optional<Doador> atualizarDoador(Integer id, Doador doadorAtualizado){
        return doadorRepository.findById(id)
                .map(doador -> {
                    doador.setNomeFantasia(doadorAtualizado.getNomeFantasia());
                    doador.setCnpj(doadorAtualizado.getCnpj());
                    doador.setNomeResponsavel(doadorAtualizado.getNomeResponsavel());
                    doador.setTelefone(doadorAtualizado.getTelefone());
                    doador.setEmail(doadorAtualizado.getEmail());
                    doador.setEnderecoCompleto(doadorAtualizado.getEnderecoCompleto());
                    return doadorRepository.save(doador);
                });
    }

    public boolean deletarDoador(Integer id){
        if (doadorRepository.existsById(id)){
            doadorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
