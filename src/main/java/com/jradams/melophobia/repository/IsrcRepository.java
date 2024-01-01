package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Isrc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IsrcRepository extends CrudRepository<Isrc, Long> {

    List<Isrc> findAllByOrderByIsrcCodeAsc();
}
