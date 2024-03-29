package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Label;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends CrudRepository<Label, Long> {

    List<Label> findAllByOrderByNameAsc();
}
