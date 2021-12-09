package com.car.yeloAssesment.repositories;

import com.car.yeloAssesment.models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {

    Page<Car> findByName(String name,Pageable pageable);

    Page<Car> findByOwner(String owner,Pageable pageable);

    @Query(value = "select u from Car u where u.name like %?1% or u.owner like %?1%")
    Page<Car> findByNameOrOwner(String name,Pageable pageable);

    @Override
    Page<Car> findAll(Pageable pageable);
}
