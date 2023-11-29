package com.project.logistics.repositories;

import com.project.logistics.models.Routes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutesRepository extends CrudRepository<Routes, Long> {
}
