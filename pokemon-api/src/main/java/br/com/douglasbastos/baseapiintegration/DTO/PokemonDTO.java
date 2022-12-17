package br.com.douglasbastos.baseapiintegration.DTO;

import br.com.douglasbastos.baseapiintegration.domain.Pokemon;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;

public class PokemonDTO {
    private Long id;
    private String name;
    private Long pokedexId;
    private String img;
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;
    private String type;

    public PokemonDTO(){
    }

    public PokemonDTO(Long id, String name, Long pokedexId, String img, Integer hp, Integer attack, Integer defense, Integer specialAttack, Integer specialDefense, Integer speed, String type) {
        this.id = id;
        this.name = name;
        this.pokedexId = pokedexId;
        this.img = img;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.type = type;
    }

    public PokemonDTO(Pokemon pokemon) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.pokedexId = pokemon.getPokedexId();
        this.img = pokemon.getImg();
        this.hp = pokemon.getHp();
        this.attack = pokemon.getAttack();
        this.defense = pokemon.getDefense();
        this.specialAttack = pokemon.getSpecialAttack();
        this.specialDefense = pokemon.getSpecialDefense();
        this.speed = pokemon.getSpeed();
        this.type = pokemon.getType();
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

    public Long getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(Long pokedexId) {
        this.pokedexId = pokedexId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
