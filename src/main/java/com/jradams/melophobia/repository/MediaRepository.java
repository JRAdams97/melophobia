package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {

    List<Media> findAllByOrderByNameAsc();
}
