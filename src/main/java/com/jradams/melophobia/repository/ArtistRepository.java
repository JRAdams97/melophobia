package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {

    List<Artist> findAllByOrderByNameAsc();
}
