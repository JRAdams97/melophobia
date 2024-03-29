package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.ReleaseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseTypeRepository extends CrudRepository<ReleaseType, Long> {

    List<ReleaseType> findAllByOrderByNameAsc();
}
