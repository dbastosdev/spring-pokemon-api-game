package br.com.douglasbastos.baseapiintegration.DTO;

import br.com.douglasbastos.baseapiintegration.domain.Round;

public class RoundDTO {
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

    public RoundDTO(){
    }

    public RoundDTO(Long id, Integer roundNumber, String playerAttacking, String playerAttackingPokemon, Integer diceResultAttack, Integer diceResultDamage, String playerDefending, String playerDefendingPokemon, Integer diceResultDefense, Integer pokemonPlayer1Hp, Integer pokemonPlayer2Hp) {
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
    }

    public RoundDTO(Round round) {
        this.id = round.getId();
        this.roundNumber = round.getRoundNumber();
        this.playerAttacking = round.getPlayerAttacking();
        this.playerAttackingPokemon = round.getPlayerAttackingPokemon();
        this.diceResultAttack = round.getDiceResultAttack();
        this.diceResultDamage = round.getDiceResultDamage();
        this.playerDefending = round.getPlayerDefending();
        this.playerDefendingPokemon = round.getPlayerDefendingPokemon();
        this.diceResultDefense = round.getDiceResultDefense();
        this.pokemonPlayer1Hp = round.getPokemonPlayer1Hp();
        this.pokemonPlayer2Hp = round.getPokemonPlayer2Hp();
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
}
