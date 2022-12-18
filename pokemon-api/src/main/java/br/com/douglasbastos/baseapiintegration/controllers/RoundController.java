package br.com.douglasbastos.baseapiintegration.controllers;

import br.com.douglasbastos.baseapiintegration.DTO.BattleDTO;
import br.com.douglasbastos.baseapiintegration.DTO.RoundDTO;
import br.com.douglasbastos.baseapiintegration.domain.Round;
import br.com.douglasbastos.baseapiintegration.services.BattleService;
import br.com.douglasbastos.baseapiintegration.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rounds")
public class RoundController {

    @Autowired
    private RoundService roundService;

    @GetMapping
    public ResponseEntity<List<RoundDTO>> findAll() {
        List<RoundDTO> list = roundService.findAll();
        return ResponseEntity.ok().body(list);
    }

}
