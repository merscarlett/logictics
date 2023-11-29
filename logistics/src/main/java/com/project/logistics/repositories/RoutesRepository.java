package com.project.logistics.repositories;

import com.project.logistics.models.Routes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutesRepository extends CrudRepository<Routes, Long> {

    List<Routes> findByCityFromAndCityTo(String cityFrom, String cityTo);
}
