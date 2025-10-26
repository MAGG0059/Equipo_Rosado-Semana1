package edu.dosw.dto;

import edu.dosw.model.Style;

public class FurnitureDTO {
    private int id;
    private String name;
    private double priceUnit;
    private Style style;

    public FurnitureDTO() {}

    public FurnitureDTO(int id, String name, double priceUnit, Style style) {
        this.id = id;
        this.name = name;
        this.priceUnit = priceUnit;
        this.style = style;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPriceUnit() { return priceUnit; }
    public void setPriceUnit(double priceUnit) { this.priceUnit = priceUnit; }

    public Style getStyle() { return style; }
    public void setStyle(Style style) { this.style = style; }
}