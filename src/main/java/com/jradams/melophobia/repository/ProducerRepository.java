package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Producer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Long> {

    List<Producer> findAllByOrderByNameAsc();
}
