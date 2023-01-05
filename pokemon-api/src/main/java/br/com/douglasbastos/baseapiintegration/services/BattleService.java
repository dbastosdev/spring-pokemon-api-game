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
        Integer lifeTest = 5;
        Integer initiativeControl = 0;

        // Loop de batalha - Enquanto os dois hp forem maior que zero. Quanto um se esgotar a batalha encerra.
        while(pokemonPlayer1.getHp() > 0 && pokemonPlayer2.getHp() > 0){
            // Abre o round
            Round round = new Round();
            round.setBattle(battle);
            round.setRoundNumber(incrementRound);
            incrementRound++;

            // troca atacante e defensor e realiza a batalha
            round = changeInitiative(round, initiativeControl, player1, player2, pokemonPlayer1, pokemonPlayer2);
            // Realiza batalha
            round = battleTurn(pokemonPlayer2, pokemonPlayer1, round, initiativeControl);
            initiativeControl++;

            /*
            Integer pokemonPlayer1Hp;
            Integer pokemonPlayer2Hp;
            */

            // TESTE DE ROUND
            System.out.println(round);
            lifeTest--;
            pokemonPlayer2.setHp(lifeTest);

            // Salva resultado do round


            // DiceRoll test
            //d100Roll();
            //d10Roll();
            //twoD10Roll();
        }

        // Update battle: Inserindo os dados nos atributos restantes para retonar o resultado da batalha.
        return new BattleDTO(battle);

    }

    // Regra de batalha
    // Atacante rola d100 de ataque que deve ser inferior ao valor do ataque do pokemon para ter sucesso
    // Defensor rola d100 de defesa que deve ser inferior ao valor de defesa do pokemon para ter sucesso
    // Se defender, hp do defensor -= d10 de dano - d10 de defesa
    // Se não defender, hp do defensor -= d10 de dano
    // Se a defesa for perfeita, d100 de defesa = 1, hp não modificado
    // Salva resultados do turno
    // Troca iniciativa
    // Inicia o outro turno
    private Round battleTurn(PokemonDTO attacking, PokemonDTO defending, Round round, Integer initiativeControl){
        Integer hpAux = 0;
        Integer control = initiativeControl % 2;

        round.setDiceResultAttack(d100Roll());

        if(round.getDiceResultAttack() < attacking.getAttack()){

            round.setDiceResultDamage(d10Roll());
            round.setDiceResultDefense(d100Roll());

            if(round.getDiceResultDefense() < defending.getDefense()){
                hpAux = defending.getHp();
                hpAux -= (round.getDiceResultDamage() - d10Roll());

                if(control == 0){
                    round.setPokemonPlayer2Hp(hpAux);
                } else{
                    round.setPokemonPlayer1Hp(hpAux);
                }
            } else{
                hpAux = defending.getHp();
                if(control == 0){
                    round.setPokemonPlayer2Hp(hpAux);
                } else{
                    round.setPokemonPlayer1Hp(hpAux);
                }
            }
        } else{
            round.setDiceResultDamage(0);
            round.setDiceResultDefense(0);

            hpAux = defending.getHp();
            hpAux -= (round.getDiceResultDamage() - d10Roll());

            if(control == 0){
                round.setPokemonPlayer2Hp(hpAux);
            } else{
                round.setPokemonPlayer1Hp(hpAux);
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
        } else {
            round.setPlayerAttacking(player2.getName());
            round.setPlayerAttackingPokemon(pokemonPlayer2.getName());
            round.setPlayerDefending(player1.getName());
            round.setPlayerDefendingPokemon(pokemonPlayer1.getName());
        }

        return round;
    }

    private Integer d100Roll(){
        Random random = new Random();
        Integer d100RollValue = random.nextInt(100) + 1;
        System.out.println("Rolagem d100: " + d100RollValue);
        return d100RollValue;
    }

    private Integer d10Roll(){
        Random random = new Random();
        Integer d10RollValue = random.nextInt(10) + 1;
        System.out.println("Rolagem d10: " + d10RollValue);
        return d10RollValue;
    }

    private Integer twoD10Roll(){
        Random random = new Random();
        Integer diceOne = random.nextInt(10) + 1;
        Integer diceTwo = random.nextInt(10) + 1;
        Integer total = diceOne + diceTwo;
        System.out.println("Rolagem 2d10: " + total);
        return total;
    }




}
