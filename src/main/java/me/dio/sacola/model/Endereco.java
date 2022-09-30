package me.dio.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {
    private String cep;
    private String complemento;
}
