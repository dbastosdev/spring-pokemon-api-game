package br.com.douglasbastos.baseapiintegration.repositories;

import br.com.douglasbastos.baseapiintegration.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    boolean existsByPokedexId(Long id);
}
