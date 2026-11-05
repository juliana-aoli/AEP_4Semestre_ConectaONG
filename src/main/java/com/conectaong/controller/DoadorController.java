package com.conectaong.controller;

import com.conectaong.model.Doador;
import com.conectaong.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    @Autowired
    private DoadorRepository doadorRepository;

    @PostMapping /*Cadastro de doador (Restaurantes/Lanchonetes)*/
    public ResponseEntity<Doador> criarDoador(@RequestBody Doador doador){
        Doador response = doadorRepository.save(doador);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}") /*Busca doador por ID*/
    public ResponseEntity<Doador> buscarPorId(@PathVariable Integer id){
        return doadorRepository.findById(id)
                .map(doador -> ResponseEntity.ok().body(doador))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping /*Lista todos os doadores*/
    public ResponseEntity<List<Doador>> listarDoadores(){
        List<Doador> response = doadorRepository.findAll();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}") /*atualizar doador*/
    public ResponseEntity<Doador> atualizarDoador(@PathVariable Integer id, @RequestBody Doador doadorAtualizado){
        return doadorRepository.findById(id)
                .map(doador -> {
                    doador.setNomeFantasia(doadorAtualizado.getNomeFantasia());
                    doador.setCnpj(doadorAtualizado.getCnpj());
                    doador.setNomeResponsavel(doadorAtualizado.getNomeResponsavel());
                    doador.setTelefone(doadorAtualizado.getTelefone());
                    doador.setEmail(doadorAtualizado.getEmail());
                    doador.setEnderecoCompleto(doadorAtualizado.getEnderecoCompleto());
                    Doador atualizado = doadorRepository.save(doador);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") /*deletar doadores*/
    public ResponseEntity<Void> deletarDoador(@PathVariable Integer id){
        if (doadorRepository.existsById(id)){
            doadorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}