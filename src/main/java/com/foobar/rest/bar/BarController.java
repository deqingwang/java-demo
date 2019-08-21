package com.foobar.rest.bar;

import com.foobar.domain.bar.Bar;
import com.foobar.persistence.bar.BarRepository;
import com.foobar.service.bar.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bar")
public class BarController {

    private BarService service;

    @Autowired
    public BarController(BarService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bar> getAll() {
        List<Bar> list = service.getAll();
        return list;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Bar get(@PathVariable("id") Integer id) {
        Bar bar = service.get(id);
        return bar;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Bar create(@RequestBody Bar body) {
        Bar saved = service.create(body);
        return saved;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Bar update(@RequestBody Bar body) {
        Bar saved = service.update(body);
        return saved;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
