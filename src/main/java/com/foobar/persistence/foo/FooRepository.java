package com.foobar.persistence.foo;

import com.foobar.domain.foo.Foo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FooRepository extends CrudRepository<Foo, Integer> {

    Optional<Foo> findById(Integer id);

}
