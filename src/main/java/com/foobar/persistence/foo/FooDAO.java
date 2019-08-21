package com.foobar.persistence.foo;

import com.foobar.domain.foo.Foo;
import com.foobar.persistence.foo.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FooDAO {

    @Autowired
    FooRepository repo;

    public List<Foo> getAll() {
        List<Foo> list = new ArrayList<>();
        repo.findAll().forEach(a -> list.add(a));
        return list;
    }

    public Foo get(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Foo create(Foo f) {
        Foo saved = repo.save(f);
        return saved;
    }

    public Foo update(Foo f) {
        repo.findById(f.getId()).orElseThrow(() -> new RuntimeException("coulc not find foo id = "+f.getId()));
        Foo saved = repo.save(f);
        return saved;
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
