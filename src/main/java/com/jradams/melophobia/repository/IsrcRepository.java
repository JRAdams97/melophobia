package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Isrc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsrcRepository extends CrudRepository<Isrc, Long> {
}
