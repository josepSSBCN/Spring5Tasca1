package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.repository;

import cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01.models.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class Repository for Sucursal to managment SQL DDBB.
 */
@Repository
public interface SucursalRepoInt extends JpaRepository<Sucursal, Integer> {
}
