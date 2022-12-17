package br.com.douglasbastos.baseapiintegration.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_master_player")
public class PokemonMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer points;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(name = "tb_master_pokemon",
                joinColumns = @JoinColumn(name = "master_id"),
                inverseJoinColumns = @JoinColumn(name = "pokemon_id"))
    private Set<Pokemon> pokemons = new HashSet<>();

    public PokemonMaster(){
    }

    public PokemonMaster(Long id, String name, Integer points) {
        this.id = id;
        this.name = name;
        this.points = points;
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

}
