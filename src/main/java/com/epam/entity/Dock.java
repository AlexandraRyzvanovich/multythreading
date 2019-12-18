package com.epam.entity;

public class Dock {
    private int id;
    private boolean loaded;
    private int capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public int isCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void unloadShip(Ship ship){
            this.loaded = true;
            ship.setLoaded(false);
            System.out.println(ship.getShipName() + "was unloaded");

    }

    public void loadShip(Ship ship){
            this.loaded = false;
            ship.setLoaded(true);
            System.out.println(ship.getShipName() + "was loaded");

    }
}
