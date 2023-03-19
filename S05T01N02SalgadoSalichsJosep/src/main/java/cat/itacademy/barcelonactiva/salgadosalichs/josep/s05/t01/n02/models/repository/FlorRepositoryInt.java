package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.repository;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n02.models.entity.FlorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class Repository for Flor to management SQL DDBB.
 */
@Repository
public interface FlorRepositoryInt extends JpaRepository<FlorEntity, Integer> {
}
