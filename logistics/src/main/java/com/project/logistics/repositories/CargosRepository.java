package com.project.logistics.repositories;

import com.project.logistics.models.Cargos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargosRepository extends CrudRepository<Cargos, Long> {
}
