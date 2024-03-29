package com.jradams.melophobia.repository;

import com.jradams.melophobia.entity.Vendor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, Long> {

    List<Vendor> findAllByOrderByNameAsc();
}
