

package com.conectaong.controller;

import com.conectaong.model.Doacao;
import com.conectaong.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    // Cria uma nova doacao
    @PostMapping
    public ResponseEntity<?> criarDoacao(@RequestBody Doacao doacao) {
        try {
            Doacao novaDoacao = doacaoService.salvar(doacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaDoacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Busca uma doacao pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> buscarPorId(@PathVariable Integer id) {
        return doacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Lista todas as doacoes
    @GetMapping
    public ResponseEntity<List<Doacao>> listaDoacoes() {
        return ResponseEntity.ok(doacaoService.listarDoacoes());
    }

    // Consulta o historico de doacoes por doador
    @GetMapping("/doador/{idDoador}")
    public ResponseEntity<List<Doacao>> listarPorDoador(
            @PathVariable Integer idDoador) {
        return ResponseEntity.ok(
                doacaoService.listarPorDoador(idDoador)
        );
    }

    // Consulta o historico de doacoes por entidade beneficente
    @GetMapping("/entidade/{idEntidade}")
    public ResponseEntity<List<Doacao>> listarPorEntidade(
            @PathVariable Integer idEntidade) {
        return ResponseEntity.ok(
                doacaoService.listarPorEntidade(idEntidade)
        );
    }

    // Atualiza os dados da doacao
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDoacao(
            @PathVariable Integer id,
            @RequestBody Doacao doacaoAtualizada) {
        try {
            return doacaoService.atualizarDoacao(id, doacaoAtualizada)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Deleta uma doacao
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoacao(@PathVariable Integer id) {
        if (doacaoService.deletarDoacao(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
