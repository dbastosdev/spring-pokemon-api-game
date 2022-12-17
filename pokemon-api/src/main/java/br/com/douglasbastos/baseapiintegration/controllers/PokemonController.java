package br.com.douglasbastos.baseapiintegration.controllers;

import br.com.douglasbastos.baseapiintegration.DTO.PokemonDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonDTOInAList;
import br.com.douglasbastos.baseapiintegration.services.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping(value = "/{offset}")
    public ResponseEntity<List<PokemonDTOInAList>> findAll(@PathVariable Long offset) throws JsonProcessingException {
        List<PokemonDTOInAList> list = pokemonService.findAll(offset);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> search(@RequestBody PokemonDTO pokemonDTOReq) throws JsonProcessingException {
        PokemonDTO pokemonDTORes = pokemonService.search(pokemonDTOReq);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pokemonDTORes.getId()).toUri();
        return ResponseEntity.created(uri).body(pokemonDTORes);
    }

}
