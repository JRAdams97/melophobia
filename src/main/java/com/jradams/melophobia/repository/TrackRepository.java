package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {

    List<Track> findAllByOrderByTitleAsc();
}
