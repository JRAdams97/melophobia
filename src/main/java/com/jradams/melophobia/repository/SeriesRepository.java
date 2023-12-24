package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends CrudRepository<Series, Long> {
}
