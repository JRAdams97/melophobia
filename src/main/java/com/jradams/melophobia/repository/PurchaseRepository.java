package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findAllByOrderByPriceAsc();
}
