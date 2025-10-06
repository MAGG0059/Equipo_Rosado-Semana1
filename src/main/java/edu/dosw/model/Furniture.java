package edu.dosw.model;

public abstract class Furniture {
    private int id;
    private String name;
    private double priceUnit;
    private Style style;

    public Furniture(int id, String name, double priceUnit, Style style) {
        this.id = id;
        this.name = name;
        this.priceUnit = priceUnit;
        this.style = style;
    }

    public double getPrice() {
        return priceUnit;
    }

    public String getName() {
        return name;
    }

    public double getPriceUnit() {
        return priceUnit;
    }

    public Style getStyle() {
        return style;
    }

}
