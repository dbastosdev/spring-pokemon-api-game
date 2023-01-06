package br.com.douglasbastos.baseapiintegration.services;

import br.com.douglasbastos.baseapiintegration.DTO.BattleDTO;
import br.com.douglasbastos.baseapiintegration.DTO.RoundDTO;
import br.com.douglasbastos.baseapiintegration.domain.Battle;
import br.com.douglasbastos.baseapiintegration.domain.Round;
import br.com.douglasbastos.baseapiintegration.repositories.BattleRepository;
import br.com.douglasbastos.baseapiintegration.repositories.RoundRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundService {

    @Autowired
    private RoundRepository roundRepository;

    @Transactional
    public List<RoundDTO> findAll() {
        List<Round> listOfRounds = roundRepository.findAll();
        List<RoundDTO> response = listOfRounds.stream().map(x -> new RoundDTO(x)).collect(Collectors.toList());
        return response;
    }

    public RoundDTO insert(RoundDTO request){

            Round entity = new Round();
            entity.setRoundNumber(request.getRoundNumber());
            entity.setPlayerAttacking(request.getPlayerAttacking());
            entity.setPlayerAttackingPokemon(request.getPlayerAttackingPokemon());
            entity.setDiceResultAttack(request.getDiceResultAttack());
            entity.setDiceResultDamage(request.getDiceResultDamage());
            entity.setPlayerDefending(request.getPlayerDefending());
            entity.setPlayerDefendingPokemon(request.getPlayerDefendingPokemon());
            entity.setDiceResultDefense(request.getDiceResultDefense());
            entity.setPokemonPlayer1Hp(request.getPokemonPlayer1Hp());
            entity.setPokemonPlayer2Hp(request.getPokemonPlayer2Hp());
            entity.setBattle(request.getBattle());

            entity = roundRepository.save(entity);

            return new RoundDTO(entity);
    }


}
