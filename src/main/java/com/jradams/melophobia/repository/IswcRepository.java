package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Iswc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IswcRepository extends CrudRepository<Iswc, Long> {
}
