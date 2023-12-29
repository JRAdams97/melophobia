package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    List<Country> findAllByOrderByCountryNameAsc();
}
