package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.CollectionPhysicalItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionPhysicalItemRepository extends CrudRepository<CollectionPhysicalItem, Long> {
}
