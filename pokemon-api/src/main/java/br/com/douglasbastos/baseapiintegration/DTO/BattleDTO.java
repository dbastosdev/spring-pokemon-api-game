package br.com.douglasbastos.baseapiintegration.DTO;

import br.com.douglasbastos.baseapiintegration.domain.Battle;
import br.com.douglasbastos.baseapiintegration.domain.Pokemon;
import br.com.douglasbastos.baseapiintegration.domain.PokemonMaster;

import java.time.LocalDate;

public class BattleDTO {
    private Long id;
    private Integer roundCount;
    private Long winnerId;

    public BattleDTO(){
    }

    public BattleDTO(Long id, Integer roundCount, Long winnerId) {
        this.id = id;
        this.roundCount = roundCount;
        this.winnerId = winnerId;
    }

    public BattleDTO(Battle battle) {
        this.id = battle.getId();
        this.roundCount = battle.getRoundCount();
        this.winnerId = battle.getWinnerId();
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


    public Long getWinner() {
        return winnerId;
    }

    public void setWinner(Long winnerId) {
        this.winnerId = winnerId;
    }
}
