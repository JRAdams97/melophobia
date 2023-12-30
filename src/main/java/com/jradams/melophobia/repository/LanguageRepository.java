package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {

    List<Language> findAllByOrderByNameAsc();
}
