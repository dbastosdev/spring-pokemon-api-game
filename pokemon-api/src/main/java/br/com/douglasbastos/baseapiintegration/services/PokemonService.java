package br.com.douglasbastos.baseapiintegration.services;

import br.com.douglasbastos.baseapiintegration.DTO.PokemonDTO;
import br.com.douglasbastos.baseapiintegration.domain.Pokemon;
import br.com.douglasbastos.baseapiintegration.repositories.PokemonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public PokemonDTO insert(PokemonDTO pokemonDTO) throws JsonProcessingException {

        if(pokemonRepository.existsByPokedexId(pokemonDTO.getPokedexId())){
            throw new RuntimeException("Este pokemon já se encontra na base de dados");
        }

        // Rest template
        // https://www.baeldung.com/rest-template
        // https://javahowtos.com/guides/107-spring/363-how-to-add-headers-to-resttemplate-in-spring.html
        // https://salesforce.stackexchange.com/questions/307659/unexpected-character-code-60-expected-a-valid-value-number-string-ar

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String baseUrl = "https://pokeapi.co/api/v2/pokemon/";
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        //ResponseEntity<Pokemon> response = restTemplate.exchange(baseUrl + pokemonDTO.getName(), HttpMethod.GET, httpEntity, Pokemon.class);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl + pokemonDTO.getName(), HttpMethod.GET, httpEntity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        JsonNode name = root.path("name");
        JsonNode pokedexId = root.path("id");
        JsonNode img = root.path("sprites").findValue("official-artwork").path("front_default");
        JsonNode hp = root.path("stats").path(0).path("base_stat");
        JsonNode attack = root.path("stats").path(1).path("base_stat");
        JsonNode defense = root.path("stats").path(2).path("base_stat");
        JsonNode specialAttack = root.path("stats").path(3).path("base_stat");
        JsonNode specialDefense = root.path("stats").path(4).path("base_stat");
        JsonNode specialSpeed = root.path("stats").path(5).path("base_stat");
        JsonNode specialType = root.path("types").findValue("type").path("name");

        Pokemon pokemon = new Pokemon();
        pokemon.setName(name.asText());
        pokemon.setPokedexId(pokedexId.asLong());
        pokemon.setImg(img.asText());
        pokemon.setHp(hp.asInt());
        pokemon.setAttack(attack.asInt());
        pokemon.setDefense(defense.asInt());
        pokemon.setSpecialAttack(specialAttack.asInt());
        pokemon.setSpecialDefense(specialDefense.asInt());
        pokemon.setSpeed(specialSpeed.asInt());
        pokemon.setType(specialType.asText());


        /*try{
            pokemon = repository.save(entity);
        }catch(RuntimeException e){
            throw new RunTimeException(e.getMessage());
        }*/

        return new PokemonDTO(pokemon);
    }


}
