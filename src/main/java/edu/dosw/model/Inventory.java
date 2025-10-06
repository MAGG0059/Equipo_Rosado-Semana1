package edu.dosw.model;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Furniture> furnitures;


    public Inventory(){
        this.furnitures = new ArrayList<>();

    }

    public void addFurniture(Furniture f){}

    public void removeFurniture(Furniture f){}

    public Furniture getFurniture(int index) {
        return furnitures.get(index);
    }


}
