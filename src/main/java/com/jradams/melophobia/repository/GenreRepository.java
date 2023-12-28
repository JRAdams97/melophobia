package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAllByOrderByNameAsc();
}
