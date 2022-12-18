package br.com.douglasbastos.baseapiintegration.controllers;

import br.com.douglasbastos.baseapiintegration.DTO.BattleDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonMasterDTO;
import br.com.douglasbastos.baseapiintegration.services.BattleService;
import br.com.douglasbastos.baseapiintegration.services.PokemonMasterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/battles")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @GetMapping
    public ResponseEntity<List<BattleDTO>> findAll() {
        List<BattleDTO> list = battleService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<BattleDTO> insert(@RequestBody BattleDTO request) {
        BattleDTO response = battleService.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
