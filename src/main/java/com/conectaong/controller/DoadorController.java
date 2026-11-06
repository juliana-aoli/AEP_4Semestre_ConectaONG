package com.conectaong.controller;

import com.conectaong.model.Doador;
import com.conectaong.repository.DoadorRepository;
import com.conectaong.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    @PostMapping /*Cadastro de doador (Restaurantes/Lanchonetes)*/
    public ResponseEntity<Doador> criarDoador(@RequestBody Doador doador){
        Doador novoDoador = doadorService.salvar(doador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDoador);
    }

    @GetMapping("/{id}") /*Busca doador por ID*/
    public ResponseEntity<Doador> buscarPorId(@PathVariable Integer id){
        return doadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping /*Lista todos os doadores*/
    public ResponseEntity<List<Doador>> listarDoadores(){
        return ResponseEntity.ok(doadorService.listarDoadores());
    }

    @PutMapping("/{id}") /*atualizar doador*/
    public ResponseEntity<Doador> atualizarDoador(@PathVariable Integer id, @RequestBody Doador doadorAtualizado){
        return doadorService.atualizarDoador(id, doadorAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") /*deletar doadores*/
    public ResponseEntity<Void> deletarDoador(@PathVariable Integer id){
        if (doadorService.deletarDoador(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}