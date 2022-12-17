package br.com.douglasbastos.baseapiintegration.DTO;

import br.com.douglasbastos.baseapiintegration.domain.Pokemon;
import br.com.douglasbastos.baseapiintegration.domain.PokemonMaster;

import java.util.HashSet;
import java.util.Set;

public class PokemonMasterDTO {
    private Long id;
    private String name;
    private Integer points;
    private Set<Pokemon> pokemons = new HashSet<>();

    public PokemonMasterDTO(){
    }

    public PokemonMasterDTO(Long id, String name, Integer points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public PokemonMasterDTO(PokemonMaster pokemonMaster){
        this.id = pokemonMaster.getId();
        this.name = pokemonMaster.getName();
        this.points = pokemonMaster.getPoints();
        this.pokemons = pokemonMaster.getPokemons();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public String toString() {
        return "PokemonMasterDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", pokemons=" + pokemons +
                '}';
    }
}
