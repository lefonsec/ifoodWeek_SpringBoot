package me.dio.sacola.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.dio.sacola.enun.FormaPagamento;
import me.dio.sacola.model.Cliente;
import me.dio.sacola.model.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class SacolaDto {

    private Long id;
    private Cliente cliente;
    private List<Item> itens = new ArrayList<>();
    private Double ValorTotal;
    private FormaPagamento formaPagamento;
    private Boolean fechada;
}
