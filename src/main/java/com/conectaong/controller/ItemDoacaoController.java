
package com.conectaong.controller;

import com.conectaong.model.ItemDoacao;
import com.conectaong.service.ItemDoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-doacao")
public class ItemDoacaoController {

    @Autowired
    private ItemDoacaoService itemDoacaoService;

    @PostMapping
    public ResponseEntity<ItemDoacao> criarItem(@RequestBody ItemDoacao item) {
        ItemDoacao novoItem = itemDoacaoService.salvar(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDoacao> buscarPorId(@PathVariable Integer id) {
        return itemDoacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ItemDoacao>> listarItens() {
        return ResponseEntity.ok(itemDoacaoService.listarItens());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDoacao> atualizarItem(
            @PathVariable Integer id,
            @RequestBody ItemDoacao itemAtualizado) {

        return itemDoacaoService.atualizarItem(id, itemAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Integer id) {
        if (itemDoacaoService.deletarItem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
