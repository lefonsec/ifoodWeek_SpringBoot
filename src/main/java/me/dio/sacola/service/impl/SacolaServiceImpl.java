package me.dio.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.dtos.ItemDto;
import me.dio.sacola.enun.FormaPagamento;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Restaurante;
import me.dio.sacola.model.Sacola;
import me.dio.sacola.repository.ItemRepository;
import me.dio.sacola.repository.ProdutoRepository;
import me.dio.sacola.repository.SacolaRepository;
import me.dio.sacola.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SacolaServiceImpl implements SacolaService {

    private final SacolaRepository sacolarepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getIdSacola());
        if (sacola.getFechada().equals(true)) {
            throw new RuntimeException("sacola fechada.");
        }
        Item itemInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getIdProduto()).orElseThrow(
                        () -> {
                            throw new IllegalArgumentException("Produto não encontrado.");
                        }
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemInserido);
        } else {
            Restaurante retauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemAdcionar = itemInserido.getProduto().getRestaurante();
            if (retauranteAtual.equals(restauranteDoItemAdcionar)) {
                itensDaSacola.add(itemInserido);
            } else {
                throw new IllegalArgumentException("não é possivel adicionar produto de restaurantes diferentes!");
            }
        }
        List<Double> valorItens = new ArrayList<Double>();
        for (Item itenDasacola : itensDaSacola) {
            Double valorTotalItem = itenDasacola.getProduto().getValorUnitario() * itenDasacola.getQuantidade();
            valorItens.add(valorTotalItem);
        }
        double valorTotalSacola =
                valorItens
                .stream()
                .mapToDouble(valorTotalCadaItem -> valorTotalCadaItem)
                .sum();
        sacola.setValorTotal(valorTotalSacola);
        sacolarepository.save(sacola);
        return itemInserido;
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolarepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("essa sacola não existe");
        });
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroformaPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("inclua intens na sacola");
        }
        FormaPagamento formaPagamento =
                numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolarepository.save(sacola);
    }
}
