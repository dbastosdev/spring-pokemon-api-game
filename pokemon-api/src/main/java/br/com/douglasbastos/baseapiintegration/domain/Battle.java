package br.com.douglasbastos.baseapiintegration.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_battle")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer roundCount;
    private Long winnerId;

    private Long player1Id;
    private Long player2Id;
    private Long pokemonPlayer1Id;
    private Long pokemonPlayer2Id;


    public Battle(){
    }

    public Battle(Long id, Integer roundCount, Long winnerId, Long player1Id, Long player2Id, Long pokemonPlayer1Id, Long pokemonPlayer2Id) {
        this.id = id;
        this.roundCount = roundCount;
        this.winnerId = winnerId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.pokemonPlayer1Id = pokemonPlayer1Id;
        this.pokemonPlayer2Id = pokemonPlayer2Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(Integer roundCount) {
        this.roundCount = roundCount;
    }


    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winner) {
        this.winnerId = winner;
    }

    public Long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Long player1Id) {
        this.player1Id = player1Id;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Long player2Id) {
        this.player2Id = player2Id;
    }

    public Long getPokemonPlayer1Id() {
        return pokemonPlayer1Id;
    }

    public void setPokemonPlayer1Id(Long pokemonPlayer1Id) {
        this.pokemonPlayer1Id = pokemonPlayer1Id;
    }

    public Long getPokemonPlayer2Id() {
        return pokemonPlayer2Id;
    }

    public void setPokemonPlayer2Id(Long pokemonPlayer2Id) {
        this.pokemonPlayer2Id = pokemonPlayer2Id;
    }
}
