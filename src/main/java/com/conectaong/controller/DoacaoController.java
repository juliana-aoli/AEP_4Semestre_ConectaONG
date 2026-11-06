package com.conectaong.controller;

import com.conectaong.model.Doacao;
import com.conectaong.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @PostMapping /*cria nova doação*/
    public ResponseEntity<Doacao> criarDoacao(@RequestBody Doacao doacao){
        Doacao novaDoacao = doacaoRepository.save(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDoacao);
    }

    @GetMapping("/{id}") /*busca doação por id*/
    public ResponseEntity<Doacao> buscarPorId(@PathVariable Integer id){
        return doacaoRepository.findById(id)
                .map(doacao -> ResponseEntity.ok().body(doacao))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping /*lista todas as doações*/
    public ResponseEntity<List<Doacao>> listaDoacoes(){
        List<Doacao> response = doacaoRepository.findAll();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}") /*atualizar status doação*/
    public ResponseEntity<Doacao> atualizarDoacao(@PathVariable Integer id, @RequestBody Doacao doacaoAtualizada){
        return doacaoRepository.findById(id)
                .map(doacao -> {
                    doacao.setDataDoacao(doacaoAtualizada.getDataDoacao());
                    doacao.setStatus(doacaoAtualizada.getStatus());
                    doacao.setEntidadeBeneficente(doacaoAtualizada.getEntidadeBeneficente());
                    Doacao atualizada = doacaoRepository.save(doacao);
                    return ResponseEntity.ok().body(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") /*deleta doação*/
    public ResponseEntity<Void> deletarDoacao(@PathVariable Integer id){
        if (doacaoRepository.existsById(id)){
            doacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}