package me.dio.sacola.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.sacola.model.Produto;
import me.dio.sacola.model.Sacola;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ItemDto {

    private Long idProduto;
    private Long idSacola;
    private Integer quantidade;
}
