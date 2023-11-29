package com.project.logistics.repositories;

import com.project.logistics.models.Vehicles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclesRepository extends CrudRepository<Vehicles, Long> {
}
