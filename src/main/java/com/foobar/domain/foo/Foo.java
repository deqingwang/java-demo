package com.foobar.domain.foo;


import javax.persistence.*;

@Entity
@Table(name = "foo")
public class Foo {

    @Id
//    @GeneratedValue -- require sequence
    @Column(name = "id")
    private Integer id;

    @Column(name = "foo")
    private String foo;

    public Foo(String foo) {
        this.foo = foo;
    }

    public Foo() {
        // Default constructor needed by JPA
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
