package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

    List<Region> findAllByOrderByNameAsc();
}
