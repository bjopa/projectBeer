package com.example.beerApp;

public class Beer {
    private Integer id;
    private String name;
    private String brewery;

    public Beer(Integer id, String name, String brewery) {
        this.id = id;
        this.name = name;
        this.brewery = brewery;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }
}

