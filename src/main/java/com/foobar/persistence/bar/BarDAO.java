package com.foobar.persistence.bar;

import com.foobar.domain.bar.Bar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class BarDAO {

    @Autowired
    BarRepository repo;

    public Bar get(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Bar create(Bar b) {
        Bar saved = repo.save(b);
        return saved;
    }

    public Bar update(Bar b) {
        Bar existing = repo.findById(b.getId()).orElseThrow(() -> new RuntimeException("can't find bar id=" + b.getId()));
        Bar saved = repo.save(b);
        return saved;
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
