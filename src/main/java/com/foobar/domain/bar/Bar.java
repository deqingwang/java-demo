package com.foobar.domain.bar;

import javax.persistence.*;

@Entity
@Table(name = "bar")
public class Bar {

    @Id
//    @GeneratedValue  - require sequence
    @Column(name = "id")
    private Integer id;

    @Column(name = "bar")
    private String bar;

    public Bar(String bar) {
        this.bar = bar;
    }

    public Bar() {
        // Default constructor needed by JPA
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }
}
