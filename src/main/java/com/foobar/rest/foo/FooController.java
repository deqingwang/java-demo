package com.foobar.rest.foo;

import com.foobar.domain.foo.Foo;
import com.foobar.service.foo.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foo")
public class FooController {

    private FooService service;

    @Autowired
    public FooController(FooService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Foo> getAll() {
        List<Foo> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Foo get(@PathVariable("id") Integer id) {
        Foo foo = service.get(id);
        return foo;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Foo create(@RequestBody Foo body) {
        Foo saved = service.create(body);
        return saved;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Foo update(@RequestBody Foo body) {
        Foo saved = service.update(body);
        return saved;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
