package br.com.douglasbastos.baseapiintegration.services;

import br.com.douglasbastos.baseapiintegration.DTO.BattleDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonMasterDTO;
import br.com.douglasbastos.baseapiintegration.domain.Battle;
import br.com.douglasbastos.baseapiintegration.domain.Round;
import br.com.douglasbastos.baseapiintegration.repositories.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private PokemonMasterService pokemonMasterService;
    @Autowired
    private RoundService roundService;


    @Transactional
    public List<BattleDTO> findAll() {
        List<Battle> listOfBattles = battleRepository.findAll();
        List<BattleDTO> response = listOfBattles.stream().map(x -> new BattleDTO(x)).collect(Collectors.toList());
        return response;
    }

    public BattleDTO insert(BattleDTO battleRequest) {

        // Setup inicial da batalha
        Battle battle = new Battle();
        battle.setRoundCount(0);
        battle.setWinnerId(null);
        battle.setPlayer1Id(battleRequest.getPlayer1Id());
        battle.setPlayer2Id(battleRequest.getPlayer2Id());
        battle.setPokemonPlayer1Id(battleRequest.getPokemonPlayer1Id());
        battle.setPokemonPlayer2Id(battleRequest.getPokemonPlayer2Id());

        try{
            battle = battleRepository.save(battle);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

        // Busca dos jogadores e pokemons
        PokemonMasterDTO player1 = pokemonMasterService.findById(battle.getPlayer1Id());
        PokemonMasterDTO player2 = pokemonMasterService.findById(battle.getPlayer2Id());
        PokemonDTO pokemonPlayer1 = pokemonService.findById(battle.getPokemonPlayer1Id());
        PokemonDTO pokemonPlayer2 = pokemonService.findById(battle.getPokemonPlayer2Id());


        // Rounds
        // Enquanto o hp é maior que zero, faz um novo round alternando o atacante

        Integer incrementRound = 0;
        //Integer lifeTest = 5;
        Integer initiativeControl = 0;

        while(pokemonPlayer1.getHp() > 0 && pokemonPlayer2.getHp() > 0){
            // Primeiro round
            Round round = new Round();
            round.setBattle(battle);
            round.setRoundNumber(incrementRound);

            // Avalia a iniciativa
            changeInitiative(round, initiativeControl, player1, player2, pokemonPlayer1, pokemonPlayer2);
            initiativeControl++;

            // TESTE DE ROUND
            //System.out.println(round);
            //lifeTest--;
            //pokemonPlayer2.setHp(lifeTest);

            // Faz a primeira batalha
                // Atacante rola d100 de ataque que deve ser inferior ao valor do ataque do pokemon para ter sucesso
                // Defensor rola d100 de defesa que deve ser inferior ao valor de defesa do pokemon para ter sucesso
                // Se defender, hp do defensor -= d10 de dano - d10 de defesa
                // Se não defender, hp do defensor -= d10 de dano
                // Se a defesa for perfeita, d100 de defesa = 1, hp não modificado
                // Troca iniciativa
                // Inicia o outro turno

            incrementRound++;
            round.setRoundNumber(incrementRound);

        }

        // Update battle

        return new BattleDTO(battle);

    }

    private void changeInitiative(Round round, Integer initiativeControl, PokemonMasterDTO player1, PokemonMasterDTO player2, PokemonDTO pokemonPlayer1, PokemonDTO pokemonPlayer2){

        Integer control = initiativeControl % 2;

        if(control == 0){
            round.setPlayerAttacking(player1.getName());
            round.setPlayerAttackingPokemon(pokemonPlayer1.getName());
            round.setPlayerDefending(player2.getName());
            round.setPlayerDefendingPokemon(pokemonPlayer2.getName());
        } else {
            round.setPlayerAttacking(player2.getName());
            round.setPlayerAttackingPokemon(pokemonPlayer2.getName());
            round.setPlayerDefending(player1.getName());
            round.setPlayerDefendingPokemon(pokemonPlayer1.getName());
        }
    }


}
