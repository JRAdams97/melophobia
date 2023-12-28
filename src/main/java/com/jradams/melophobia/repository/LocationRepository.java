package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    List<Location> findAllByOrderByCityAsc();
}
