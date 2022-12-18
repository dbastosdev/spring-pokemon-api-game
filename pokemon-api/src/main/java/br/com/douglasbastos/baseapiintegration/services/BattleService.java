package br.com.douglasbastos.baseapiintegration.services;

import br.com.douglasbastos.baseapiintegration.DTO.BattleDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonDTO;
import br.com.douglasbastos.baseapiintegration.DTO.PokemonMasterDTO;
import br.com.douglasbastos.baseapiintegration.domain.Battle;
import br.com.douglasbastos.baseapiintegration.domain.Pokemon;
import br.com.douglasbastos.baseapiintegration.domain.PokemonMaster;
import br.com.douglasbastos.baseapiintegration.repositories.BattleRepository;
import br.com.douglasbastos.baseapiintegration.repositories.PokemonMasterRepository;
import br.com.douglasbastos.baseapiintegration.repositories.PokemonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;

    @Transactional
    public List<BattleDTO> findAll() {
        List<Battle> listOfBattles = battleRepository.findAll();
        List<BattleDTO> response = listOfBattles.stream().map(x -> new BattleDTO(x)).collect(Collectors.toList());
        return response;
    }

    public BattleDTO insert(Long player1Id, Long player2Id, Long pokemonPlayer1Id, Long pokemonPlayer2Id) {

        Battle entity = new Battle();

        // Iniciliza a batalha
        entity.setRoundCount(0);
        entity.setWinnerId(null);

//        entity.setCreatedAt(LocalDateTime.now());
//        entity.setUpdatedAt(entity.getCreatedAt());
//        entity.setName(customerReq.getName());
//        entity.setLogin(customerReq.getLogin());
//        entity.setPassword(customerReq.getPassword());
//        entity.setCpf(customerReq.getCpf());
//        entity = repository.save(entity);
//
//        if(customerReq.getZipcode() != null && customerReq.getNumber() != null){
//
//            AddressReq addressReq = new AddressReq();
//
//            addressReq.setZipcode(customerReq.getZipcode());
//            addressReq.setNumber(customerReq.getNumber());
//            addressReq.setCustomerForAddress(entity);
//
//            AddressRes addressRes = addressService.insert(addressReq);
//
//            entity.setAddress(new Address(addressRes));
//        }
//
//
//
        return new BattleDTO(entity);

    }


}
