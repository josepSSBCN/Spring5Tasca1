package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.repository;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class Repository for Countries to management SQL DDBB.
 */
@Repository
public interface CountryRepoInt extends JpaRepository<Country, Integer> {
}
