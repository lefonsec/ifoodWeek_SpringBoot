package me.dio.sacola.service;

import me.dio.sacola.dtos.ItemDto;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Sacola;
import org.springframework.stereotype.Service;

@Service
public interface SacolaService {

    Item incluirItemSacola(ItemDto itemDto);

    Sacola verSacola(Long id);

    Sacola fecharSacola(Long id, int formaPagamento);
}
