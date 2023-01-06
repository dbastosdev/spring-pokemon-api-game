package br.com.douglasbastos.baseapiintegration.services;

import br.com.douglasbastos.baseapiintegration.DTO.BattleDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonMasterDTO;
import br.com.douglasbastos.baseapiintegration.DTO.RoundDTO;
import br.com.douglasbastos.baseapiintegration.domain.Battle;
import br.com.douglasbastos.baseapiintegration.domain.PokemonMaster;
import br.com.douglasbastos.baseapiintegration.domain.Round;
import br.com.douglasbastos.baseapiintegration.repositories.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
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

        // Enquanto o hp for maior que zero, faz um novo round alternando o atacante e defensor

        // Inicialização de variáveis acessórias
        Integer incrementRound = 0;
        Integer initiativeControl = 0;

        // Loop de batalha - Enquanto os dois hp forem maior que zero. Quanto um se esgotar a batalha encerra.
        while(pokemonPlayer1.getHp() > 0 && pokemonPlayer2.getHp() > 0){
            battle.setRoundCount(incrementRound);
            // Abre o round
            Round round = new Round();
            round.setBattle(battle);
            round.setRoundNumber(incrementRound);
            incrementRound++;

            // troca atacante e defensor e realiza a batalha
            round = changeInitiative(round, initiativeControl, player1, player2, pokemonPlayer1, pokemonPlayer2);
            initiativeControl++;

            // Atualiza dados do pokemon
            pokemonPlayer1.setHp(round.getPokemonPlayer1Hp());
            pokemonPlayer2.setHp(round.getPokemonPlayer2Hp());


            // TESTE DE ROUND
            //System.out.println(round);

            // Salva resultado do round
            roundService.insert(new RoundDTO(round));

        }

        // Apura vencedor da batalha
        if(pokemonPlayer1.getHp() <= 0){
            battle.setWinnerId(player2.getId());
            pokemonMasterService.updatePoints(player2.getId());
        } else {
            battle.setWinnerId(player1.getId());
            pokemonMasterService.updatePoints(player1.getId());
        }

        battle = battleRepository.save(battle);
        // Update battle: Inserindo os dados nos atributos restantes para retonar o resultado da batalha.
        return new BattleDTO(battle);

    }

    // Regra de batalha
    private Round battleTurn(PokemonDTO attacking, PokemonDTO defending, Round round, Integer control){
        Integer hpDefense = 0;
        Integer hpAtack = 0;

        round.setDiceResultAttack(d100Roll());

        if(round.getDiceResultAttack() < attacking.getAttack()){

            round.setDiceResultDamage(d10Roll());
            round.setDiceResultDefense(d100Roll());

            if(round.getDiceResultDefense() < defending.getDefense()){
                hpDefense = defending.getHp();
                Integer damageModified = Math.toIntExact(Math.round(round.getDiceResultDamage() * 0.5));
                hpDefense = Math.toIntExact(hpDefense - damageModified);
                round.setDiceResultDamage(damageModified);
                hpAtack = attacking.getHp();

                if(control == 1){
                    round.setPokemonPlayer2Hp(hpDefense);
                    round.setPokemonPlayer1Hp(hpAtack);
                } else {
                    round.setPokemonPlayer1Hp(hpDefense);
                    round.setPokemonPlayer2Hp(hpAtack);
                }

            } else{
                hpDefense = defending.getHp();
                hpAtack = attacking.getHp();

                if(control == 1){
                    round.setPokemonPlayer2Hp(hpDefense - round.getDiceResultDamage());
                    round.setPokemonPlayer1Hp(hpAtack);
                } else {
                    round.setPokemonPlayer1Hp(hpDefense - round.getDiceResultDamage());
                    round.setPokemonPlayer2Hp(hpAtack);
                }


            }
        } else{
            round.setDiceResultDamage(0);
            round.setDiceResultDefense(0);

            hpDefense = defending.getHp();
            hpAtack = attacking.getHp();

            if(control == 1){
                round.setPokemonPlayer2Hp(hpDefense);
                round.setPokemonPlayer1Hp(hpAtack);
            } else {
                round.setPokemonPlayer1Hp(hpDefense);
                round.setPokemonPlayer2Hp(hpAtack);
            }
        }


        return round;
    }

    private Round changeInitiative(Round round, Integer initiativeControl, PokemonMasterDTO player1, PokemonMasterDTO player2, PokemonDTO pokemonPlayer1, PokemonDTO pokemonPlayer2){

        Integer control = initiativeControl % 2;

        if(control == 0){
            round.setPlayerAttacking(player1.getName());
            round.setPlayerAttackingPokemon(pokemonPlayer1.getName());
            round.setPlayerDefending(player2.getName());
            round.setPlayerDefendingPokemon(pokemonPlayer2.getName());
            // Realiza batalha - ataque / defesa / round
            round = battleTurn(pokemonPlayer1, pokemonPlayer2, round, 1);
        } else {
            round.setPlayerAttacking(player2.getName());
            round.setPlayerAttackingPokemon(pokemonPlayer2.getName());
            round.setPlayerDefending(player1.getName());
            round.setPlayerDefendingPokemon(pokemonPlayer1.getName());
            // Realiza batalha - ataque / defesa / round
            round = battleTurn(pokemonPlayer2, pokemonPlayer1, round, 2);
        }

        return round;
    }

    private Integer d100Roll(){
        Random random = new Random();
        Integer d100RollValue = random.nextInt(100) + 1;
        return d100RollValue;
    }

    private Integer d10Roll(){
        Random random = new Random();
        Integer d10RollValue = random.nextInt(10) + 1;
        return d10RollValue;
    }

}
