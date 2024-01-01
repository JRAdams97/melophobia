package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Iswc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IswcRepository extends CrudRepository<Iswc, Long> {

    List<Iswc> findAllByOrderByIswcCodeAsc();
}
