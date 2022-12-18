package br.com.douglasbastos.baseapiintegration.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_round")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer roundNumber;

    private String playerAttacking;
    private String playerAttackingPokemon;
    private Integer diceResultAttack;
    private Integer diceResultDamage;

    private String playerDefending;
    private String playerDefendingPokemon;
    private Integer diceResultDefense;

    private Integer pokemonPlayer1Hp;
    private Integer pokemonPlayer2Hp;

    @ManyToOne
    @JoinColumn(name = "battle_id", referencedColumnName = "id")
    private Battle battle;

    public Round(){
    }

    public Round(Long id, Integer roundNumber, String playerAttacking, String playerAttackingPokemon, Integer diceResultAttack, Integer diceResultDamage, String playerDefending, String playerDefendingPokemon, Integer diceResultDefense, Integer pokemonPlayer1Hp, Integer pokemonPlayer2Hp, Battle battle) {
        this.id = id;
        this.roundNumber = roundNumber;
        this.playerAttacking = playerAttacking;
        this.playerAttackingPokemon = playerAttackingPokemon;
        this.diceResultAttack = diceResultAttack;
        this.diceResultDamage = diceResultDamage;
        this.playerDefending = playerDefending;
        this.playerDefendingPokemon = playerDefendingPokemon;
        this.diceResultDefense = diceResultDefense;
        this.pokemonPlayer1Hp = pokemonPlayer1Hp;
        this.pokemonPlayer2Hp = pokemonPlayer2Hp;
        this.battle = battle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getPlayerAttacking() {
        return playerAttacking;
    }

    public void setPlayerAttacking(String playerAttacking) {
        this.playerAttacking = playerAttacking;
    }

    public String getPlayerAttackingPokemon() {
        return playerAttackingPokemon;
    }

    public void setPlayerAttackingPokemon(String playerAttackingPokemon) {
        this.playerAttackingPokemon = playerAttackingPokemon;
    }

    public Integer getDiceResultAttack() {
        return diceResultAttack;
    }

    public void setDiceResultAttack(Integer diceResultAttack) {
        this.diceResultAttack = diceResultAttack;
    }

    public Integer getDiceResultDamage() {
        return diceResultDamage;
    }

    public void setDiceResultDamage(Integer diceResultDamage) {
        this.diceResultDamage = diceResultDamage;
    }

    public String getPlayerDefending() {
        return playerDefending;
    }

    public void setPlayerDefending(String playerDefending) {
        this.playerDefending = playerDefending;
    }

    public String getPlayerDefendingPokemon() {
        return playerDefendingPokemon;
    }

    public void setPlayerDefendingPokemon(String playerDefendingPokemon) {
        this.playerDefendingPokemon = playerDefendingPokemon;
    }

    public Integer getDiceResultDefense() {
        return diceResultDefense;
    }

    public void setDiceResultDefense(Integer diceResultDefense) {
        this.diceResultDefense = diceResultDefense;
    }

    public Integer getPokemonPlayer1Hp() {
        return pokemonPlayer1Hp;
    }

    public void setPokemonPlayer1Hp(Integer pokemonPlayer1Hp) {
        this.pokemonPlayer1Hp = pokemonPlayer1Hp;
    }

    public Integer getPokemonPlayer2Hp() {
        return pokemonPlayer2Hp;
    }

    public void setPokemonPlayer2Hp(Integer pokemonPlayer2Hp) {
        this.pokemonPlayer2Hp = pokemonPlayer2Hp;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", roundNumber=" + roundNumber +
                ", playerAttacking='" + playerAttacking + '\'' +
                ", playerAttackingPokemon='" + playerAttackingPokemon + '\'' +
                ", diceResultAttack=" + diceResultAttack +
                ", diceResultDamage=" + diceResultDamage +
                ", playerDefending='" + playerDefending + '\'' +
                ", playerDefendingPokemon='" + playerDefendingPokemon + '\'' +
                ", diceResultDefense=" + diceResultDefense +
                ", pokemonPlayer1Hp=" + pokemonPlayer1Hp +
                ", pokemonPlayer2Hp=" + pokemonPlayer2Hp +
                ", battle=" + battle +
                '}';
    }
}
