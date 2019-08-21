package com.foobar.service.bar;

import com.foobar.domain.bar.Bar;
import com.foobar.persistence.bar.BarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarService {

    private BarDAO barDAO;

    @Autowired
    public BarService(BarDAO barDAO) {
        this.barDAO = barDAO;
    }

    public List<Bar> getAll() {return  barDAO.getAll(); }

    public Bar get(Integer id) {
        return  barDAO.get(id);
    }

    public Bar create(Bar b) {
        return barDAO.create(b);
    }

    public Bar update(Bar b) {
        return barDAO.update(b);
    }

    public void delete(Integer id) {
        barDAO.delete(id);
    }
}
