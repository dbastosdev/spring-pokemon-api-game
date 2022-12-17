package br.com.douglasbastos.baseapiintegration.repositories;

import br.com.douglasbastos.baseapiintegration.domain.Pokemon;
import br.com.douglasbastos.baseapiintegration.domain.PokemonMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonMasterRepository extends JpaRepository<PokemonMaster, Long> {
    boolean existsByName(String name);
}
