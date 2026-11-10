
package com.conectaong.service;

import com.conectaong.model.ItemDoacao;
import com.conectaong.repository.ItemDoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemDoacaoService {

    @Autowired
    private ItemDoacaoRepository itemDoacaoRepository;

    public ItemDoacao salvar(ItemDoacao item) {
        return itemDoacaoRepository.save(item);
    }

    public List<ItemDoacao> listarItens() {
        return itemDoacaoRepository.findAll();
    }

    public Optional<ItemDoacao> buscarPorId(Integer id) {
        return itemDoacaoRepository.findById(id);
    }

    public Optional<ItemDoacao> atualizarItem(Integer id, ItemDoacao itemAtualizado) {
        return itemDoacaoRepository.findById(id)
                .map(item -> {
                    item.setDescricao(itemAtualizado.getDescricao());
                    item.setCategoria(itemAtualizado.getCategoria());
                    item.setQuantidade(itemAtualizado.getQuantidade());
                    item.setUnidadeMedida(itemAtualizado.getUnidadeMedida());
                    item.setDataValidade(itemAtualizado.getDataValidade());
                    item.setDoacao(itemAtualizado.getDoacao());
                    return itemDoacaoRepository.save(item);
                });
    }

    public boolean deletarItem(Integer id) {
        if (itemDoacaoRepository.existsById(id)) {
            itemDoacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
