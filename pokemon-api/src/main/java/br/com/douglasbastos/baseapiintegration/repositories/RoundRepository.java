package br.com.douglasbastos.baseapiintegration.repositories;

import br.com.douglasbastos.baseapiintegration.domain.Battle;
import br.com.douglasbastos.baseapiintegration.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
}
