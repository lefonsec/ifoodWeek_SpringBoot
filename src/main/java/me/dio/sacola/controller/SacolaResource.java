package me.dio.sacola.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.dio.sacola.dtos.ItemDto;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Sacola;
import me.dio.sacola.service.SacolaService;
import org.springframework.web.bind.annotation.*;

@Api(value ="/iood-devweek/sacola")
@RestController
@RequestMapping("/iood-devweek/sacola")
@RequiredArgsConstructor
public class SacolaResource {

    private final SacolaService sacolaService;


    @PostMapping
    public Item incluirItemSacola(@RequestBody ItemDto itemDto) {
        return sacolaService.incluirItemSacola(itemDto);
    }

    @GetMapping(value = "/{id}")
    public Sacola verSacola(@PathVariable Long id) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping(value = "/{id}")
    public Sacola fecharSacola(@PathVariable Long id, @RequestBody int formaPagamento) {
        return sacolaService.fecharSacola(id, formaPagamento);
    }
}
