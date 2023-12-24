package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Release;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends CrudRepository<Release, Long> {
}
