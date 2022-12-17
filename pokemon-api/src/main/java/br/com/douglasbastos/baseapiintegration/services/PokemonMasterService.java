package br.com.douglasbastos.baseapiintegration.services;

import br.com.douglasbastos.baseapiintegration.DTO.PokemonDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonMasterDTO;
import br.com.douglasbastos.baseapiintegration.domain.Pokemon;
import br.com.douglasbastos.baseapiintegration.domain.PokemonMaster;
import br.com.douglasbastos.baseapiintegration.repositories.PokemonMasterRepository;
import br.com.douglasbastos.baseapiintegration.repositories.PokemonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class PokemonMasterService {

    @Autowired
    private PokemonMasterRepository pokemonMasterRepository;
    @Autowired
    private PokemonService pokemonService;

    @Transactional
    public PokemonMasterDTO insert(PokemonMasterDTO request) throws JsonProcessingException {

        if(pokemonMasterRepository.existsByName(request.getName())){
            throw new RuntimeException("Este mestre pokemon já se encontra na base de dados");
        }

        PokemonMaster pokemonMaster = new PokemonMaster();

        pokemonMaster.setName(request.getName());
        pokemonMaster.setPoints(0);

        for(Pokemon p : request.getPokemons()){
            // Insere o pokemon na base de dados de pokemons
            PokemonDTO pokemonDTO = new PokemonDTO();
            pokemonDTO.setName(p.getName());
            pokemonDTO = pokemonService.search(pokemonDTO);

            // Adiciona pokemon a lista de pokemons do mestre pokemon
            pokemonMaster.getPokemons().add(new Pokemon(pokemonDTO));
        }

        try{
            pokemonMaster = pokemonMasterRepository.save(pokemonMaster);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

        return new PokemonMasterDTO(pokemonMaster);
    }


}
