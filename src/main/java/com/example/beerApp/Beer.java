package com.example.beerApp;

public class Beer {
    private Integer id;
    private String name;
    private String brewery;
    private String style;
    private double alcohol;
    private String description;



    public Beer(Integer id, String name, String brewery, String style, double alcohol, String description) {
        this.id = id;
        this.name = name;
        this.brewery = brewery;
        this.style = style;
        this.alcohol = alcohol;
        this.description = description;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

