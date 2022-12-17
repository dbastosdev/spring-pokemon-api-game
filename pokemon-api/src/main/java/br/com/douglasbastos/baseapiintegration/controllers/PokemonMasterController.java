package br.com.douglasbastos.baseapiintegration.controllers;

import br.com.douglasbastos.baseapiintegration.DTO.PokemonDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonMasterDTO;
import br.com.douglasbastos.baseapiintegration.services.PokemonMasterService;
import br.com.douglasbastos.baseapiintegration.services.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pokemon-master")
public class PokemonMasterController {

    @Autowired
    private PokemonMasterService pokemonMasterService;

    @PostMapping
    public ResponseEntity<PokemonMasterDTO> insert(@RequestBody PokemonMasterDTO request) throws JsonProcessingException {
        PokemonMasterDTO response = pokemonMasterService.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
