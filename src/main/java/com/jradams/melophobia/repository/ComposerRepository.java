package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Composer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComposerRepository extends CrudRepository<Composer, Long> {

    List<Composer> findAllByOrderByNameAsc();
}
