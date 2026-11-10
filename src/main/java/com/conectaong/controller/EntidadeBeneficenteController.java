
package com.conectaong.controller;

import com.conectaong.model.EntidadeBeneficente;
import com.conectaong.service.EntidadeBeneficenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entidades-beneficentes")
public class EntidadeBeneficenteController {

    @Autowired
    private EntidadeBeneficenteService entidadeBeneficenteService;

    @PostMapping
    public ResponseEntity<EntidadeBeneficente> criarEntidade(
            @RequestBody EntidadeBeneficente entidade) {

        EntidadeBeneficente novaEntidade = entidadeBeneficenteService.salvar(entidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaEntidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadeBeneficente> buscarPorId(@PathVariable Integer id) {

        return entidadeBeneficenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EntidadeBeneficente>> listarEntidades() {

        return ResponseEntity.ok(entidadeBeneficenteService.listarEntidades());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntidadeBeneficente> atualizarEntidade(
            @PathVariable Integer id,
            @RequestBody EntidadeBeneficente entidadeAtualizada) {

        return entidadeBeneficenteService.atualizarEntidade(id, entidadeAtualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntidade(@PathVariable Integer id) {

        if (entidadeBeneficenteService.deletarEntidade(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
