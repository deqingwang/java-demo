package com.foobar.service.foo;

import com.foobar.domain.foo.Foo;
import com.foobar.persistence.foo.FooDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    private FooDAO fooDAO;

    @Autowired
    public FooService(FooDAO fooDAO) {
        this.fooDAO = fooDAO;
    }

    public Foo get(Integer id) {
        return  fooDAO.get(id);
    }

    public Foo create(Foo b) {
        return fooDAO.create(b);
    }

    public Foo update(Foo b) {
        return fooDAO.update(b);
    }

    public void delete(Integer id) {
        fooDAO.delete(id);
    }
}
