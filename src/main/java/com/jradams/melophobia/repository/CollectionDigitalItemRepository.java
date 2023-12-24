package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.CollectionDigitalItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionDigitalItemRepository extends CrudRepository<CollectionDigitalItem, Long> {
}
