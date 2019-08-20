package com.foobar.persistence.bar;

import com.foobar.domain.bar.Bar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BarRepository extends CrudRepository<Bar, Integer> {

    Optional<Bar> findById(Integer id);

}