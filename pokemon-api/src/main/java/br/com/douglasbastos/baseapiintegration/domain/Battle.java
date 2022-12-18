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

    public Battle(){
    }

    public Battle(Long id, Integer roundCount, Long winnerId) {
        this.id = id;
        this.roundCount = roundCount;
        this.winnerId = winnerId;
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
}
